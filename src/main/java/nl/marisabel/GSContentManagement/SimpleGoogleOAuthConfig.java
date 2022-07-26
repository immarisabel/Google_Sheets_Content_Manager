package nl.marisabel.GSContentManagement;

import java.io.File;

public class SimpleGoogleOAuthConfig
{
    protected final File secretsFile;
    protected final File tokenCacheFile;

    /**
     * @param secretsFile must exist for us to read from
     * @param tokenCacheFile we'll create it if it doesn't exist and overwrite it if it does
     */

    public SimpleGoogleOAuthConfig(File secretsFile, File tokenCacheFile)
    {
        this.secretsFile = secretsFile;
        this.tokenCacheFile = tokenCacheFile;
    }

    public File getSecretsFile()
    {
        return secretsFile;
    }

    public File getTokenCacheFile()
    {
        return tokenCacheFile;
    }
}