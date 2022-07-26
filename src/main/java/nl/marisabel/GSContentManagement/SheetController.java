package nl.marisabel.GSContentManagement;

import com.google.api.services.sheets.v4.Sheets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.security.GeneralSecurityException;


@Controller
public class SheetController {

    @Autowired
    private SheetsApiService sheets;

    @RequestMapping("/home")
    public String greetName(Model model, SheetModel sheetModel) throws GeneralSecurityException, IOException {

        sheetModel.setGreeting(String.valueOf(sheets.readColumnFromRow(1,0)));
        sheetModel.setName(String.valueOf(sheets.readColumnFromRow(1,1)));
        sheetModel.setQuote(String.valueOf(sheets.readColumnFromRow(1,2)));

        System.out.println(sheetModel);
        model.addAttribute("sheetModel", sheetModel);
        return "home";
    }

}
