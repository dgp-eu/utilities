package io.github.dgp_eu.utilities.cli;

import java.util.List;

import io.github.dgp_eu.tools.core.CommonInteractiveClass;
import io.github.dgp_eu.tools.core.FileOperationsClass;
import io.github.dgp_eu.tools.core.LogExposureClass;
import picocli.CommandLine;
import picocli.CommandLine.Mixin;

/**
 * Captures sub-folder from a Given Folder into Log file
 */
@CommandLine.Command(name = "GetSubFoldersFromFolders",
                     description = "Captures sub-folders from a Given Folder into Log file")
class GetSubFoldersFromFolders implements Runnable {

    /**
     * adds the options defined in 
     * CommonInteractiveClass.FolderNameOptionMixinClass to this command
     */
    @Mixin
    private final CommonInteractiveClass.FolderNameOptionMixinClass optFolderNames = new CommonInteractiveClass.FolderNameOptionMixinClass();

    @Override
    public void run() {
        final String[] inFolders = optFolderNames.getFolderNames();
        for (final String strFolder : inFolders) {
            final List<String> arraySubFolders = FileOperationsClass.RetrievingSubClass.getSubFoldersFromFolder(strFolder);
            final String strFeedback = String.format("Considering folder %s following sub-folders were found: %s", strFolder, arraySubFolders);
            LogExposureClass.LOGGER.info(strFeedback);
        }
    }

    /**
     * Private constructor to prevent instantiation
     */
    protected GetSubFoldersFromFolders() {
        super();
    }

}