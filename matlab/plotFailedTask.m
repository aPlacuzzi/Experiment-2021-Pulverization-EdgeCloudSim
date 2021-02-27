function [] = plotFailedTask(rowOfset1, rowOfset2, columnOfset, yLabel, appType, calculatePercentage, withError, folderPath, folderNum, baseFileName, outputDir)
    numOfSimulations = getConfiguration(2);
    startOfMobileDeviceLoop = getConfiguration(3);
    stepOfMobileDeviceLoop = getConfiguration(4);
    endOfMobileDeviceLoop = getConfiguration(5);
    xTickLabelCoefficient = getConfiguration(6);
    
    scenarioType = getConfiguration(7);
    legends = getConfiguration(8);
    numOfMobileDevices = (endOfMobileDeviceLoop - startOfMobileDeviceLoop)/stepOfMobileDeviceLoop + 1;

    pos=getConfiguration(9);
    
    all_results = loadData(rowOfset1, columnOfset, appType, calculatePercentage, folderPath, folderNum);
    min_results = zeros(size(scenarioType,2), numOfMobileDevices);
    max_results = zeros(size(scenarioType,2), numOfMobileDevices);
    
    all_resultsCloud = loadData(rowOfset2, columnOfset, appType, calculatePercentage, folderPath, folderNum);
    
    if(numOfSimulations == 1)
        results = all_results;
		resultsCloud = all_resultsCloud;
    else
        results = mean(all_results); %still 3d matrix but 1xMxN format
		resultsCloud = mean(all_resultsCloud);
    end
    
    results = squeeze(results); %remove singleton dimensions
	resultsCloud = squeeze(resultsCloud); %remove singleton dimensions
    
    for i=1:size(scenarioType,2)
        for j=1:numOfMobileDevices
            x=all_results(:,i,j);                    % Create Data
            SEM = std(x)/sqrt(length(x));            % Standard Error
            ts = tinv([0.05  0.95],length(x)-1);   	% T-Score
            CI = mean(x) + ts*SEM;                   % Confidence Intervals

            if(CI(1) < 0)
                CI(1) = 0;
            end

            if(CI(2) < 0)
                CI(2) = 0;
            end

            min_results(i,j) = results(i,j) - SEM;%- CI(1);
            max_results(i,j) = results(i,j) + SEM;%CI(2) - results(i,j);
        end
    end
    
    types = zeros(1,numOfMobileDevices);
    for i=1:numOfMobileDevices
        types(i)=startOfMobileDeviceLoop+((i-1)*stepOfMobileDeviceLoop);
    end
    
    hFig = figure;
    set(hFig, 'Units','centimeters');
    set(hFig, 'Position',pos);
    set(0,'DefaultAxesFontName','Times New Roman');
    set(0,'DefaultTextFontName','Times New Roman');
    set(0,'DefaultAxesFontSize',10);
    set(0,'DefaultTextFontSize',12);
    
    for i=1:1:numOfMobileDevices
        xIndex=startOfMobileDeviceLoop+((i-1)*stepOfMobileDeviceLoop);
        
        markers = getConfiguration(50);
        for j=1:size(scenarioType,2)
            plot(xIndex, results(j,i),char(markers(j)),'MarkerFaceColor',getConfiguration(20+j),'color',getConfiguration(20+j),'LineWidth',1.5);
            hold on;
        end
    end
    
    for j=1:size(scenarioType,2)
        if(withError)
            plot(types, results(j,:),char(markers(j)),'color',getConfiguration(20+j),'LineWidth',1.5);
             hold on;
            plot(types, min_results(j,:),char(markers(j)),'color',getConfiguration(20+j),'LineWidth',0.5);
             hold on;
            plot(types, max_results(j,:),char(markers(j)),'color',getConfiguration(20+j),'LineWidth',0.5);
        else
            plot(types, results(j,:),char(markers(j)),'color',getConfiguration(20+j),'LineWidth',1.5);
			      hold on;
            ar = area(types, resultsCloud(j,:));
            set(ar, 'EdgeColor', getConfiguration(20+j));
            set(ar, 'FaceColor', getConfiguration(20+j));
            set(ar, 'FaceAlpha', 0.5);
        end
        hold on;
    end
    set(gca,'color','none');
    
    lgnd = legend(legends,'Location','NorthWest');
    legend 'boxoff';
    if(getConfiguration(20) == 1)
        set(lgnd,'color','none');
    end

    hold off;
    axis square
    xlabel(getConfiguration(10));
    set(gca,'XTick', (startOfMobileDeviceLoop*xTickLabelCoefficient):50:endOfMobileDeviceLoop);
    set(gca,'XTickLabel', (startOfMobileDeviceLoop*xTickLabelCoefficient):50:endOfMobileDeviceLoop);
    ylabel(yLabel);
    set(gca,'XLim',[startOfMobileDeviceLoop, endOfMobileDeviceLoop]);
	set(gca,'YLim',[0, 100.0]);
    
    set(get(gca,'Xlabel'),'FontSize',12)
    set(get(gca,'Ylabel'),'FontSize',12)
    set(lgnd,'FontSize',11)
    
    if(getConfiguration(11) == 1)
        set(hFig, 'PaperUnits', 'centimeters');
        set(hFig, 'PaperPositionMode', 'manual');
        set(hFig, 'PaperPosition',[0 0 pos(3) pos(4)]);
        set(gcf, 'PaperSize', [pos(3) pos(4)]); %Keep the same paper size
        filename = fullfile(outputDir,strcat(baseFileName,'_',appType,'.pdf'));
        print(gcf, filename, '-svgconvert')
    end
end

