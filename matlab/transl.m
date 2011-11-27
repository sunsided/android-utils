function mtx = transl(x, y, z)

    if ~exist('x', 'var')
        x = 1;
    end
    
    if ~exist('y', 'var')
        x = 2;
    end
    
    if ~exist('z', 'var')
        x = 3;
    end
    
    mtx =  [
     1,  0,  0,  0;
	 0,  1,  0,  0;
	 0,  0,  1,  0;
	 x,  y,  z,  1;
     ];
    
end