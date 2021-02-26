function [] = plotAvgServiceTime(baseDir, folderNum, baseFileName, withError, outputDir)

    plotGenericResult(1, 5, 'Service Time (sec)', 'ALL_APPS', '', withError, baseDir, folderNum, strcat(baseFileName, 'Service_Time'), outputDir);
    
    plotGenericResult(2, 5, 'Service Time on Edge (sec)', 'ALL_APPS', '', withError, baseDir, folderNum, strcat(baseFileName, 'Service_Time_on_Edge'), outputDir);
    
    plotGenericResult(3, 5, 'Service Time on Cloud (sec)', 'ALL_APPS', '', withError, baseDir, folderNum, strcat(baseFileName, 'Service_Time_on_Cloud'), outputDir);
    
end