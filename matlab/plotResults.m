function [] = plotResults(baseDir, folderNum, baseFileName, withError)
    plotAvgFailedTask(baseDir, folderNum, baseFileName, withError);
    
    plotAvgNetworkDelay(baseDir, folderNum, baseFileName, withError);
    
    %plotAvgProcessingTime(baseDir, baseFileName, withError);
    
    %plotAvgServiceTime(baseDir, baseFileName, withError);
	
	%plotAvgVmUtilization(baseDir, baseFileName, withError);
	
	%plotTaskFailureReason(baseDir, baseFileName, withError);
end