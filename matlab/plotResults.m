function [] = plotResults(baseDir, baseFileName, withError)
    plotAvgFailedTask(baseDir, baseFileName, withError);
    
    plotAvgNetworkDelay(baseDir, baseFileName, withError);
    
    plotAvgProcessingTime(baseDir, baseFileName, withError);
    
    plotAvgServiceTime(baseDir, baseFileName, withError);
	
	plotAvgVmUtilization(baseDir, baseFileName, withError);
	
	plotTaskFailureReason(baseDir, baseFileName, withError);
end