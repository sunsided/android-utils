function box(center, extent, linestyle)

    extent = abs(extent);
    axis_min = 2*(center-extent);
    axis_max = 2*(center+extent);
    max_pt = center+extent;

    if ~exist('linestyle', 'var')
        linestyle = 'b:';
    end
    
    btl = [ 
        center(1)-extent(1)
        center(2)+extent(2)
        center(3)+extent(3)
        ];
    btr = [ 
        center(1)+extent(1)
        center(2)+extent(2)
        center(3)+extent(3)
        ];
    
    bbl = [ 
        center(1)-extent(1)
        center(2)-extent(2)
        center(3)+extent(3)
        ];
    bbr = [ 
        center(1)+extent(1)
        center(2)-extent(2)
        center(3)+extent(3)
        ];
    
    
    
    ftl = [ 
        center(1)-extent(1)
        center(2)+extent(2)
        center(3)-extent(3)
        ];
    ftr = [ 
        center(1)+extent(1)
        center(2)+extent(2)
        center(3)-extent(3)
        ];
    
    fbl = [ 
        center(1)-extent(1)
        center(2)-extent(2)
        center(3)-extent(3)
        ];
    fbr = [ 
        center(1)+extent(1)
        center(2)-extent(2)
        center(3)-extent(3)
        ];

    % figure;
    plot3([btl(1) btr(1)], [btl(2) btr(2)], [btl(3) btr(3)], 'k', 'LineWidth', 2); hold on;
    plot3([ftl(1) ftr(1)], [ftl(2) ftr(2)], [ftl(3) ftr(3)], 'k', 'LineWidth', 2);
    plot3([bbl(1) bbr(1)], [bbl(2) bbr(2)], [bbl(3) bbr(3)], 'k', 'LineWidth', 2); hold on;
    plot3([fbl(1) fbr(1)], [fbl(2) fbr(2)], [fbl(3) fbr(3)], 'k', 'LineWidth', 2);
    
    plot3([ftl(1) fbl(1)], [ftl(2) fbr(2)], [ftl(3) fbr(3)], 'k', 'LineWidth', 2); hold on;
    plot3([ftr(1) fbr(1)], [ftr(2) fbr(2)], [ftr(3) fbr(3)], 'k', 'LineWidth', 2); hold on;
    plot3([btl(1) bbl(1)], [btl(2) bbr(2)], [btl(3) bbr(3)], 'k', 'LineWidth', 2); hold on;
    plot3([btr(1) bbr(1)], [btr(2) bbr(2)], [btr(3) bbr(3)], 'k', 'LineWidth', 2); hold on;
    
    plot3([fbl(1) bbl(1)], [fbl(2) bbl(2)], [fbl(3) bbl(3)], 'k', 'LineWidth', 2); hold on;
    plot3([fbr(1) bbr(1)], [fbr(2) bbr(2)], [fbr(3) bbr(3)], 'k', 'LineWidth', 2); hold on;
    plot3([ftl(1) btl(1)], [ftl(2) btl(2)], [ftl(3) btl(3)], 'k', 'LineWidth', 2); hold on;
    plot3([ftr(1) btr(1)], [ftr(2) btr(2)], [ftr(3) btr(3)], 'k', 'LineWidth', 2); hold on;
    
    plot3([center(1) max_pt(1)], [center(2) max_pt(2)], [center(3) max_pt(3)], linestyle);
    
    axis([axis_min(1) axis_max(1) axis_min(2) axis_max(2) axis_min(3) axis_max(3)]);
    xlabel('X'); ylabel('Y'); zlabel('Z');
    grid on;
 
end