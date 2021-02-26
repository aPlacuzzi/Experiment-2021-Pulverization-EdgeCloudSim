function [] = plotTaskFailureReason(baseDir, baseFileName, withError)

    plotGenericResult(1, 10, 'Failed Task due to VM Capacity (%)', 'ALL_APPS', 'percentage_for_all', withError, baseDir, strcat(baseFileName, 'Failed_Task_due_to_VM_Capacity'));
    
    plotGenericResult(1, 11, 'Failed Task due to Mobility (%)', 'ALL_APPS', 'percentage_for_all', withError, baseDir, strcat(baseFileName, 'Failed_Task_due_to_Mobility'));
    
    plotGenericResult(5, 5, 'Failed Tasks due to WLAN failure (%)', 'ALL_APPS', 'percentage_for_all', withError, baseDir, strcat(baseFileName, 'Failed_Task_due_to_WLAN'));
    
    plotGenericResult(5, 7, 'Failed Tasks due to WAN failure (%)', 'ALL_APPS', 'percentage_for_all', withError, baseDir, strcat(baseFileName, 'Failed_Task_due_to_WAN'));
	
	plotGenericResult(1, 10, 'Failed Task due to VM Capacity (%)', 'ALL_APPS', '', withError, baseDir, strcat(baseFileName, 'Failed_Task_due_to_VM_Capacity_Pure'));
    
    plotGenericResult(1, 11, 'Failed Task due to Mobility (%)', 'ALL_APPS', '', withError, baseDir, strcat(baseFileName, 'Failed_Task_due_to_Mobility_Pure'));
    
    plotGenericResult(5, 5, 'Failed Tasks due to WLAN failure (%)', 'ALL_APPS', '', withError, baseDir, strcat(baseFileName, 'Failed_Task_due_to_WLAN_Pure'));
    
    plotGenericResult(5, 7, 'Failed Tasks due to WAN failure (%)', 'ALL_APPS', '', withError, baseDir, strcat(baseFileName, 'Failed_Task_due_to_WAN_Pure'));
    
end