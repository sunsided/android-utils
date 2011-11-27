%% Matrizen definieren
translation = transl(10, 20, 30)
rotation_y = rotyrhs(deg2rad(90))

% Kombinieren
transformation = translation * rotation_y

% Umdrehen für Matlab
transformation = transformation';

%% Verrechnen
point  = [1; 2; 3; 1];
vector = [1; 2; 3; 0];

transformed_point  = transformation * point
transformed_vector = transformation * vector
