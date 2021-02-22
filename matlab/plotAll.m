function [] = plotAll()
    plotResults('C:\Users\Placu\softcomp\EdgeCloudSimConfigGenerator\output\results\behaviourAndCommunication12', 'EaC12_', false);
    plotResults('C:\Users\Placu\softcomp\EdgeCloudSimConfigGenerator\output\results\behaviourWithCommunication2', 'EwC2_', false);
    plotResults('C:\Users\Placu\softcomp\EdgeCloudSimConfigGenerator\output\results\onlyCommunication2', 'C2_', false);
	
	plotResults('C:\Users\Placu\softcomp\EdgeCloudSimConfigGenerator\output\results\behaviourAndCommunication12', 'EaC12_withStdv_', true);
    plotResults('C:\Users\Placu\softcomp\EdgeCloudSimConfigGenerator\output\results\behaviourWithCommunication2', 'EwC2_withStdv_', true);
    plotResults('C:\Users\Placu\softcomp\EdgeCloudSimConfigGenerator\output\results\onlyCommunication2', 'C2_withStdv_', true);
end