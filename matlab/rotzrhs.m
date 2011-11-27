function mtx = rotzrhs(theta)

    if ~exist('theta', 'var')
        theta = 90*pi/180;
    end

    c = round(cos(theta) * 10^5) * 10^-5;
    s = round(sin(theta) * 10^5) * 10^-5;
    
    mtx =  [
     c,  s,  0,  0;
	 0,  1,  0,  0;
	-s,  c,  1,  0;
	 0,  0,  0,  1;
     ];
    
end