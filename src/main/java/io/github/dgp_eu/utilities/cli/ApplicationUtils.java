/** Copyright 2026 Daniel-Gheorghe Popiniuc */
package io.github.dgp_eu.utilities.cli;

import io.github.dgp_eu.tools.core.CommonInteractiveClass;
import picocli.CommandLine;

/**
 * Main Command Line
 */
@CommandLine.Command(
    name = "top",
    subcommands = {
            AnalyzeColumnsFromCsvFiles.class,
            AnalyzePomFiles.class,
            ArchiveFolders.class,
            CaptureChecksumsOfFilesFromFoldersIntoCsvFile.class,
            CaptureImportsFromJavaSourceFilesIntoCsvFile.class,
            CaptureWindowsApplicationsInstalledIntoCsvFile.class,
            CleanOlderFilesFromFolder.class,
            GetSubFoldersFromFolders.class
    }
)
public class ApplicationUtils {

    /**
     * Application logic
     * @param args input arguments
     */
    public static void main( final String[] args ) {
        CommonInteractiveClass.startMeUpWithParameters("logs/Utilities", "/utilities-pom.xml");
        final int intUtilsExitCode = new CommandLine(new ApplicationUtils()).execute(args);
        CommonInteractiveClass.shutMeDownWithParameters(intUtilsExitCode, args[0]);
    }

}
