function [] = plotResults(baseDir, baseFileName)
    plotAvgFailedTask(baseDir, baseFileName);
    
    plotAvgNetworkDelay(baseDir, baseFileName);
    
    plotAvgProcessingTime(baseDir, baseFileName);
    
    plotAvgServiceTime(baseDir, baseFileName);
	
	plotAvgVmUtilization(baseDir, baseFileName);
	
	plotTaskFailureReason(baseDir, baseFileName);
end