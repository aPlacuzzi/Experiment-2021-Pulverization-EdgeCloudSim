function [] = plotAvgProcessingTime(baseDir, baseFileName, withError, outputDir)

    plotGenericResult(1, 6, 'Processing Time (sec)', 'ALL_APPS', '', withError, baseDir, strcat(baseFileName, 'Processing_Time'), outputDir);
    
    plotGenericResult(2, 6, 'Processing Time on Edge (sec)', 'ALL_APPS', '', withError, baseDir, strcat(baseFileName, 'Processing_Time_on_Edge'), outputDir);
    
    plotGenericResult(3, 6, 'Processing Time on Cloud (sec)', 'ALL_APPS', '', withError, baseDir, strcat(baseFileName, 'Processing_Time_on_Cloud'), outputDir);
    
end