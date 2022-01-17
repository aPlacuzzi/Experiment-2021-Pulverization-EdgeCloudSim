function [] = plotAll()
	addpath(fullfile('./', 'matlab'));
	initEnv();
	mkdir('./output/charts/behaviourAndCommunication');
	mkdir('./output/charts/behaviourWithCommunication');
	mkdir('./output/charts/onlyCommunication');
    plotResults('./output/results/behaviourAndCommunication', 0, 'EaC0_', false, './output/charts/behaviourAndCommunication', [50]);
    plotResults('./output/results/behaviourWithCommunication', 0, 'EwC0_', false, './output/charts/behaviourWithCommunication', [40]);
    plotResults('./output/results/onlyCommunication', 0, 'C0_', false, './output/charts/onlyCommunication', [80, 100]);
	
    plotResults('./output/results/behaviourAndCommunication', 6, 'EaC6_', false, './output/charts/behaviourAndCommunication', [50, 100]);
    plotResults('./output/results/behaviourWithCommunication', 1, 'EwC1_', false, './output/charts/behaviourWithCommunication', [40, 100]);
    plotResults('./output/results/onlyCommunication', 1, 'C1_', false, './output/charts/onlyCommunication', [80, 100]);
	
%	plotResults('./output/results/behaviourAndCommunication', 6, 'EaC6_withStdv_', true, './output/charts/behaviourAndCommunication', [50, 100]);
%    plotResults('./output/results/behaviourWithCommunication', 1, 'EwC1_withStdv_', true, './output/charts/behaviourWithCommunication', [40, 100]);
%    plotResults('./output/results/onlyCommunication', 1, 'C1_withStdv_', true, './output/charts/onlyCommunication', [80, 100]);
	
	plotResults('./output/results/behaviourAndCommunication', 12, 'EaC12_', false, './output/charts/behaviourAndCommunication', [50, 100]);
    plotResults('./output/results/behaviourWithCommunication', 2, 'EwC2_', false, './output/charts/behaviourWithCommunication', [40, 100]);
    plotResults('./output/results/onlyCommunication', 2, 'C2_', false, './output/charts/onlyCommunication', [80, 100]);
	
%	plotResults('./output/results/behaviourAndCommunication', 12, 'EaC12_withStdv_', true, './output/charts/behaviourAndCommunication', [50, 100]);
%    plotResults('./output/results/behaviourWithCommunication', 2, 'EwC2_withStdv_', true, './output/charts/behaviourWithCommunication', [40, 100]);
%    plotResults('./output/results/onlyCommunication', 2, 'C2_withStdv_', true, './output/charts/onlyCommunication', [80, 100]);
	
	plotResults('./output/results/behaviourAndCommunication', 18, 'EaC18_', false, './output/charts/behaviourAndCommunication', [50, 100]);
    plotResults('./output/results/behaviourWithCommunication', 3, 'EwC3_', false, './output/charts/behaviourWithCommunication', [40, 100]);
    plotResults('./output/results/onlyCommunication', 3, 'C3_', false, './output/charts/onlyCommunication', [80, 100]);
	
%	plotResults('./output/results/behaviourAndCommunication', 18, 'EaC18_withStdv_', true, './output/charts/behaviourAndCommunication', [50, 100]);
%    plotResults('./output/results/behaviourWithCommunication', 3, 'EwC3_withStdv_', true, './output/charts/behaviourWithCommunication', [40, 100]);
%    plotResults('./output/results/onlyCommunication', 3, 'C3_withStdv_', true, './output/charts/onlyCommunication', [80, 100]);

    plotResults('./output/results/behaviourAndCommunication', 24, 'EaC24_', false, './output/charts/behaviourAndCommunication', [50]);
    plotResults('./output/results/behaviourWithCommunication', 4, 'EwC4_', false, './output/charts/behaviourWithCommunication', [40]);
    plotResults('./output/results/onlyCommunication', 4, 'C4_', false, './output/charts/onlyCommunication', [80, 100]);
end