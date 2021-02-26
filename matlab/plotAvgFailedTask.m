function [] = plotAvgFailedTask(baseDir, folderNum, baseFileName, withError, outputDir)

    if (!withError) 
      plotFailedTask(1, 3, 2, 'Failed Tasks (%)', 'ALL_APPS', 'percentage_for_all', withError, baseDir, folderNum, strcat(baseFileName, 'Failed_Tasks_Mix'), outputDir);
    end
    plotGenericResult(1, 2, 'Failed Tasks (%)', 'ALL_APPS', 'percentage_for_all', withError, baseDir, folderNum, strcat(baseFileName, 'Failed_Tasks'), outputDir);
    
    plotGenericResult(2, 2, 'Failed Tasks on Edge (%)', 'ALL_APPS', 'percentage_for_all', withError, baseDir, folderNum, strcat(baseFileName, 'Failed_Tasks_on_Edge'), outputDir);
    
    plotGenericResult(3, 2, 'Failed Tasks on Cloud (%)', 'ALL_APPS', 'percentage_for_all', withError, baseDir, folderNum, strcat(baseFileName, 'Failed_Tasks_on_Cloud'), outputDir);    
end