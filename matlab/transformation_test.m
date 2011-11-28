%% Matrizen definieren
translation = transl(10, 20, 30)
rotation_y = rotyrhs(deg2rad(-90))

% Kombinieren
% Hinweis: Multiplikation von links mit Zeile-Spalte ist identisch mit
% von rechts mit nicht transponierter.
transformation = ([2 0 0 0; 0 2 0 0; 0 0 2 0; 0 0 0 1] * rotation_y * translation)';

%% Verrechnen
point  = [0.5; 0.5; 0.5; 1];
vector = [0.5; 0.5; 0.5; 0];

transformed_point  = transformation * point
transformed_vector = transformation * vector

close all;
box([vector(1) vector(2) vector(3)]); box([transformed_vector(1) transformed_vector(2) transformed_vector(3)], 'r');


inv(transformation)