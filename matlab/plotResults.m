function [] = plotResults(baseDir, folderNum, baseFileName, withError, outputDir)
    plotAvgFailedTask(baseDir, folderNum, baseFileName, withError, outputDir);
    
    plotAvgNetworkDelay(baseDir, folderNum, baseFileName, withError, outputDir);
    
    plotAvgProcessingTime(baseDir, baseFileName, withError, outputDir);
    
    plotAvgServiceTime(baseDir, baseFileName, withError, outputDir);
	
	plotAvgVmUtilization(baseDir, baseFileName, withError, outputDir);
	
	plotTaskFailureReason(baseDir, baseFileName, withError, outputDir);
end