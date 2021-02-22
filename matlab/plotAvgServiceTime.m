function [] = plotAvgServiceTime(baseDir, baseFileName, withError)

    plotGenericResult(1, 5, 'Service Time (sec)', 'ALL_APPS', '', withError, baseDir, strcat(baseFileName, 'Service_Time'));
    
    plotGenericResult(2, 5, 'Service Time on Edge (sec)', 'ALL_APPS', '', withError, baseDir, strcat(baseFileName, 'Service_Time_on_Edge'));
    
    plotGenericResult(3, 5, 'Service Time on Cloud (sec)', 'ALL_APPS', '', withError, baseDir, strcat(baseFileName, 'Service_Time_on_Cloud'));
    
end