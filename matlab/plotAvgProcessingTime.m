function [] = plotAvgProcessingTime(baseDir, baseFileName)

    plotGenericResult(1, 6, 'Processing Time (sec)', 'ALL_APPS', '', baseDir, strcat(baseFileName, 'Processing_Time'));
    
    plotGenericResult(2, 6, 'Processing Time on Edge (sec)', 'ALL_APPS', '', baseDir, strcat(baseFileName, 'Processing_Time_on_Edge'));
    
    plotGenericResult(3, 6, 'Processing Time on Cloud (sec)', 'ALL_APPS', '', baseDir, strcat(baseFileName, 'Processing_Time_on_Cloud'));
    
end