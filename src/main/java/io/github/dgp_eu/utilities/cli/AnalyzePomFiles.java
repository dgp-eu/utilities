package io.github.dgp_eu.utilities.cli;

import io.github.dgp_eu.tools.core.CommonInteractiveClass;
import io.github.dgp_eu.tools.core.LogExposureClass;
import io.github.dgp_eu.tools.core.ProjectClass;
import picocli.CommandLine;
import picocli.CommandLine.Mixin;

/**
 * Captures sub-folder from a Given Folder into Log file
 */
@CommandLine.Command(name = "AnalyzePomFiles",
                     description = "Exposes information from one or multiple Project Object Model (Apache Maven configuration file)")
class AnalyzePomFiles implements Runnable {
    /**
     * adds the options defined in 
     * CommonInteractiveClass.FileNameOptionMixinClass to this command
     */
    @Mixin
    private final CommonInteractiveClass.InFileNameOptionMixinClass optFileNames = new CommonInteractiveClass.InFileNameOptionMixinClass();

    @Override
    public void run() {
        final String strFeedbackThis = String.format("For this project relevant POM information is: {%s}", ProjectClass.ApplicationSubClass.getApplicationDetails());
        LogExposureClass.LOGGER.info(strFeedbackThis);
        final String[] inFiles = optFileNames.getInFileNames();
        for (final String strFileName : inFiles) {
            ProjectClass.setPomFile(strFileName);
            final String strFeedback = String.format("For given POM file %s relevant information is: {%s}", strFileName, ProjectClass.ApplicationSubClass.getApplicationDetails());
            LogExposureClass.LOGGER.info(strFeedback);
        }
    }

    /**
     * Private constructor to prevent instantiation
     */
    protected AnalyzePomFiles() {
        super();
    }

}