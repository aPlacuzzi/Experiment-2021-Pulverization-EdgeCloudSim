function [] = plotTaskFailureReason(baseDir, folderNum, baseFileName, withError, outputDir)

    plotGenericResult(1, 10, 'Failed Task due to VM Capacity (%)', 'ALL_APPS', 'percentage_for_all', withError, baseDir, folderNum, strcat(baseFileName, 'Failed_Task_due_to_VM_Capacity'), outputDir);
    
    plotGenericResult(1, 11, 'Failed Task due to Mobility (%)', 'ALL_APPS', 'percentage_for_all', withError, baseDir, folderNum, strcat(baseFileName, 'Failed_Task_due_to_Mobility'), outputDir);
    
    plotGenericResult(5, 5, 'Failed Tasks due to WLAN failure (%)', 'ALL_APPS', 'percentage_for_all', withError, baseDir, folderNum, strcat(baseFileName, 'Failed_Task_due_to_WLAN'), outputDir);
    
    plotGenericResult(5, 7, 'Failed Tasks due to WAN failure (%)', 'ALL_APPS', 'percentage_for_all', withError, baseDir, folderNum, strcat(baseFileName, 'Failed_Task_due_to_WAN'), outputDir);
	
	plotGenericResult(1, 10, 'Failed Task due to VM Capacity (%)', 'ALL_APPS', '', withError, baseDir, folderNum, strcat(baseFileName, 'Failed_Task_due_to_VM_Capacity_Pure'), outputDir);
    
    plotGenericResult(1, 11, 'Failed Task due to Mobility (%)', 'ALL_APPS', '', withError, baseDir, folderNum, strcat(baseFileName, 'Failed_Task_due_to_Mobility_Pure'), outputDir);
    
    plotGenericResult(5, 5, 'Failed Tasks due to WLAN failure (%)', 'ALL_APPS', '', withError, baseDir, folderNum, strcat(baseFileName, 'Failed_Task_due_to_WLAN_Pure'), outputDir);
    
    plotGenericResult(5, 7, 'Failed Tasks due to WAN failure (%)', 'ALL_APPS', '', withError, baseDir, folderNum, strcat(baseFileName, 'Failed_Task_due_to_WAN_Pure'), outputDir);
    
end