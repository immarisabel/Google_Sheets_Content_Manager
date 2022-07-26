package nl.marisabel.GSContentManagement;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.List;

@Service
public class SheetsApiService {

    private static String APP_NAME = "GSCM";
    private static String SHEET_ID = "158HYv0JFdtFEdldLffQrIRw8oADbxP1o0VywRgixsNk";
    private static Sheets sheetService;

    // READ CREDENTIALS FROM JSON FILE IN RESOURCES
    private static Credential authorize() throws IOException, GeneralSecurityException {

        InputStream in = SheetsApiService.class.getResourceAsStream("/credentials.json");
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(GsonFactory.getDefaultInstance(), new InputStreamReader(in));
        List<String> scopes = List.of(SheetsScopes.SPREADSHEETS);

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(), GsonFactory.getDefaultInstance(), clientSecrets, scopes).setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
                .setAccessType("offline").build();

        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");

    }

    // INITIATE AUTHORIZATION
    private static Sheets getSheetService() throws GeneralSecurityException, IOException {
        Credential credential = authorize();
        return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(), GsonFactory.getDefaultInstance(), credential).setApplicationName(APP_NAME).build();
    }

    // READ DESIRED RANGE
    private static List<Object> readRowFromSheet(int n) throws GeneralSecurityException, IOException {
        String range = "greet!A1:G20";
        ValueRange response = sheetService.spreadsheets().values().get(SHEET_ID, range).execute();
        List<List<Object>> values = response.getValues();
        return values.get(n);
    }

    // GET VALUE FROM FIELD
    public static Object readColumnFromRow(int row, int column) throws GeneralSecurityException, IOException {
        sheetService = getSheetService();
        return readRowFromSheet(row).get(column);
    }


}

