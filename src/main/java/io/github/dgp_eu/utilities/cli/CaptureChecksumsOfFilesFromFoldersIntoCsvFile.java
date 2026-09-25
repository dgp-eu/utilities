package io.github.dgp_eu.utilities.cli;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import io.github.dgp_eu.tools.core.CommonInteractiveClass;
import io.github.dgp_eu.tools.core.FileStatisticsClass;
import io.github.dgp_eu.tools.core.LogExposureClass;
import io.github.dgp_eu.tools.core.time.TimingClass;
import picocli.CommandLine;
import picocli.CommandLine.Mixin;

/**
 * clean files older than a given number of days
 */
@CommandLine.Command(name = "CaptureChecksumsOfFilesFromFolderIntoCsvFile",
                     description = "Get statistics for all files within a given folder")
class CaptureChecksumsOfFilesFromFoldersIntoCsvFile implements Runnable {

    /**
     * adds the options defined in 
     * CommonInteractiveClass.FolderNameOptionMixinClass to this command
     */
    @Mixin
    private final CommonInteractiveClass.FolderNameOptionMixinClass optFolderNames = new CommonInteractiveClass.FolderNameOptionMixinClass();
    /**
     * adds the options defined in 
     * CommonInteractiveClass.OutFileNameOptionMixinClass to this command
     */
    @Mixin
    private final CommonInteractiveClass.OutFileNameOptionMixinClass optOutFileName = new CommonInteractiveClass.OutFileNameOptionMixinClass();

    @Override
    public void run() {
        final String[] inAlgorithms = {"SHA-256", "SHA3-256"};
        FileStatisticsClass.setChecksumAlgorithms(inAlgorithms);
        final String[] inFolders = optFolderNames.getFolderNames();
        final String outCsvFile = optOutFileName.getOutFileName();
        for (final String strFolder : inFolders) {
            final ZonedDateTime startComputeTime = ZonedDateTime.now(ZoneId.systemDefault());
            FileStatisticsClass.captureFileStatisticsFromFolder(strFolder, outCsvFile);
            final ZonedDateTime zStopTimeStamp = ZonedDateTime.now(ZoneId.systemDefault());
            final Duration objDuration = Duration.between(startComputeTime, zStopTimeStamp);
            final String strFeedback = String.format("For the folder %s calculated checksums are stored in the file %s operation completed in %s (which means %s | %s)", strFolder, outCsvFile, objDuration.toString(), TimingClass.AgingSubClass.computeAgingIntoHumanReadableWords(startComputeTime, zStopTimeStamp), TimingClass.AgingSubClass.computeAgingIntoTimeClock(startComputeTime, zStopTimeStamp));
            LogExposureClass.LOGGER.info(strFeedback);
        }
    }

    /**
     * Constructor
     */
    protected CaptureChecksumsOfFilesFromFoldersIntoCsvFile() {
        super();
    }
}