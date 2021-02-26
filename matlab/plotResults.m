function [] = plotResults(baseDir, folderNum, baseFileName, withError, outputDir)
    plotAvgFailedTask(baseDir, folderNum, baseFileName, withError, outputDir);
    
    plotAvgNetworkDelay(baseDir, folderNum, baseFileName, withError, outputDir);
    
    plotAvgProcessingTime(baseDir, folderNum, baseFileName, withError, outputDir);
    
    plotAvgServiceTime(baseDir, folderNum, baseFileName, withError, outputDir);
	
	plotAvgVmUtilization(baseDir, folderNum, baseFileName, withError, outputDir);
	
	plotTaskFailureReason(baseDir, folderNum, baseFileName, withError, outputDir);
end