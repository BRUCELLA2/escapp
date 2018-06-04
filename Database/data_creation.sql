/* INSERT INTO escapp_user TABLE */

INSERT INTO public.escapp_user(id, login, email, password)
	VALUES 
		(DEFAULT, 'user1', 'user1@mail.fr', '$2a$10$b38C6Bz41bFlDJPJBXuC8.ESB57HjTEqZHmlDR8FLD4VvJieRp86G'),
		(DEFAULT, 'user2', 'user2@mail.fr', '$2a$10$b38C6Bz41bFlDJPJBXuC8.ESB57HjTEqZHmlDR8FLD4VvJieRp86G'),
		(DEFAULT, 'admin', 'admin@mail.fr', '$2a$10$b38C6Bz41bFlDJPJBXuC8.ESB57HjTEqZHmlDR8FLD4VvJieRp86G'),
		(DEFAULT, 'Pierre34', 'pierre34@mail.fr', '$2a$10$b38C6Bz41bFlDJPJBXuC8.ESB57HjTEqZHmlDR8FLD4VvJieRp86G');

/* INSERT INTO role_user TABLE */
INSERT INTO public.role_user(role, escapp_user)
	VALUES 
		('Admin', 3);

/* INSERT INTO comment TABLE */

INSERT INTO public.comment(id, text, target_type, id_comment_target, escapp_user)
	VALUES 
		(DEFAULT, 'Premier commentaire site1', 'Site', 1, 1),
		(DEFAULT, 'Premier commentaire secteur 1', 'Sector', 1, 2 ),
		(DEFAULT, '2nd commentaire site 1', 'Site', 1, 1),
		(DEFAULT, 'Le soleil se couche vers 16h en juillet. La voie sèche très vite après une précipatation.', 'Route', 5, 4),
		(DEFAULT, 'Un beau site abordable.', 'Site', 4, 4);

/* INSERT INTO site TABLE */

INSERT INTO public.site(id, name, department, municipality, description)
	VALUES 
		(DEFAULT, 'Site 1', '034', 'Monpellier', 'Site 1 - Département de l''héraut - Ville de Monpellier'),
		(DEFAULT, 'Site 2', '030', 'Nimes', 'Site 2 - Département du gard - Ville de Nimes'),
		(DEFAULT, 'Site 3', '030', 'Uzès', 'Site 3 - Département du gard - Ville d''uzès'),
		(DEFAULT, 'Ravine des Colimaçons', '974', 'Saint Leu', 'L''ensemble du site a été rééquipé en 2011 / 2012 par le CRFFME et le club 7AW. De belles lignes vous attendent en rive droite ou gauche !
		 Le site fait l''objet d''une action environnementale avec une plantation d''espèces endémique de l''ile. De nombreux blocs sont ouverts et fléchés dans la ravine.');


/* INSERT INTO sector TABLE */

INSERT INTO public.sector(id, name, description, site_id)
	VALUES 
		(DEFAULT, 'Secteur 1 - Site 1', 'Premier secteur', 1),
		(DEFAULT, 'Secteur 2 - Site 1', 'Second secteur', 1),
		(DEFAULT, 'Secteur 1 - Site 3', 'Premier secteur', 3),
		(DEFAULT, 'Secteur Dunes', 'Restez sur le Secteur Dunes, les autres secteurs sont en cours d''équipement.', 4),
		(DEFAULT, 'Secteur SDF', 'Secteur en cours d''équipement, non sécurisé et très instables. Le secteur "Dunes" est à préférer', 4),
		(DEFAULT, 'Secteur Ficus', 'Secteur en cours d''équipement, non sécurisé et très instables. Le secteur "Dunes" est à préférer', 4);


/* INSERT INTO route TABLE */

INSERT INTO public.route(id, sector_id, name, grade, points_nb, description)
	VALUES 
		(DEFAULT, 1, 'Voie 1', '5a', 2, 'Description de la voie'),
		(DEFAULT, 1, 'Voie 2', '4a', 1, 'Description de la voie'),
		(DEFAULT, 2, 'Voie 1', '6b', 1, 'Description de la voie'),
		(DEFAULT, 3, 'Voie 1', '3c', 3, 'Description de la voie'),
		(DEFAULT, 4, 'Jabba the Hutt', '6b', 5, 'Voie obèse, des plis sur le ventre. Position en dalle popularisée dans les années 1980 dans le crux, équipée la veille d''un "Jedi" .... on dirait question pour un champion'),
		(DEFAULT, 4, 'Mano négra', '5a', 6, '1 Mètre cube de terre descendu de la fissure, de quoi se salir les pattes ! en un groupe atypique...'),
		(DEFAULT, 4, 'Thérorème', '7b', 1, 'Belle ligne, une vire à protéger, un mousquetonage difficile, un équipmeent à réfléchir, 2 lignes qui se croisent, mathématiquement compliquée...'),
		(DEFAULT, 4, 'Diago folle', '5b', 6, 'Ligne évidente, un clin d''oeil au trailer responsable de l''équipement des sites de la région qui me fournit en matos...'), 
		(DEFAULT, 4, 'Belle de nuit', '6b', 5, 'Ouverte il y a 20 ans par J-Marc Caron avec un léger abus du perfo, ... une autre époque, une belle ligne, à faire !'),
		(DEFAULT, 4, 'Liane folie', '6a', 4, 'Ha cette liane, interrogation : coupe, coupe pas, soyons fou !!!'),
		(DEFAULT, 4, 'Plomb en biais', '7a', 4, 'Une canalisation "Lianesque" trop belle pour être coupée, un vol à se mettre de travers, ouarf trop drôle, tout trouvé!'),
		(DEFAULT, 5, 'Cashmire', '6c', 1, ''),
		(DEFAULT, 5, 'Coco beach', '5c', 1, ''),
		(DEFAULT, 6, 'Banana spit', '6b', 1, ''),
		(DEFAULT, 6, 'Cache toit', '6b', 1, ''),
		(DEFAULT, 6, 'Le fils sûr', '5b', 1, '');





/* INSERT INTO lenght TABLE */

INSERT INTO public.length(id, length, grade, points_nb, description, route_id)
	VALUES 
		(DEFAULT, 25, '5a', 1, 'Première longueur', 1),
		(DEFAULT, 20, '5a', 1, 'Seconde longueur', 1),
		(DEFAULT, 100, '4a', 1, 'Première longueur', 2),
		(DEFAULT, 50, '6b', 1, 'Première longueur', 3),
		(DEFAULT, 250, '3d', 2, 'Première longueur', 4),
		(DEFAULT, 75, '3b', 1, 'Seconde longueur', 4),
		(DEFAULT, 20, '6a', 2, 'Dalle "fracturée"', 5),
		(DEFAULT, 10, '6c', 1, 'Dalle, passages fins, difficulté obligatoire car deux passages sont bien au-dessus des points, et sans moyen de protéger.', 5),
		(DEFAULT, 10, '6c', 1, 'Dalle, passages en adhérence', 5),
		(DEFAULT, 10, '6b', 1, 'Dalle "sculptée"', 5);



/* INSERT INTO topo TABLE */

INSERT INTO public.topo(id, name, department, is_borrowable, pdf_file_name, municipality, end_date_borrow, borrower_id, description, owner_id)
	VALUES 
		(DEFAULT, 'Topo 1 : Autour de Monpellier', '034', true, 'topo1_mtp.pdf', 'Monpellier', null, null, 'Topo 1 autour de montpellier dans le 34 (topo1_mtp.pdf), empruntable appartenant à user1', 1),
		(DEFAULT, 'Topo 2 : Autour de Nimes', '030', false, 'topo2_nimes.pdf', 'Nimes', null, null, 'Topo 2 autour de nimes dans le 30 (topo2_nimes.pdf, non empruntable appartenant à user1', 1),
		(DEFAULT, 'Topo 3 : topo générale', '034', true, 'topo3.pdf', null, null, null, 'Topo 3 générale sur le dep 34, empruntable appartenant à user1', 1),
		(DEFAULT, 'Topo 4 : topo Alès', '030', true, 'topo4_Ales.pdf', 'Alès', TIMESTAMP '2018-05-15 00:00:00', 1, 'emprunté par user 1 et appartenant à user2 - Fin location 15/05/2018', 2),
		(DEFAULT, 'Topo sans description', '048', false, 'topoSansDescription.pdf', 'Mende', null, null, null, 2);
