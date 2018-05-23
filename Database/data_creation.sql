\connect escapp

/* INSERT INTO escapp_user TABLE */

INSERT INTO public.escapp_user(id, login, email, password)
	VALUES 
		(DEFAULT, 'user1', 'user1@mail.fr', '$2a$10$b38C6Bz41bFlDJPJBXuC8.ESB57HjTEqZHmlDR8FLD4VvJieRp86G'),
		(DEFAULT, 'user2', 'user2@mail.fr', '$2a$10$b38C6Bz41bFlDJPJBXuC8.ESB57HjTEqZHmlDR8FLD4VvJieRp86G'),
		(DEFAULT, 'admin', 'admin@mail.fr', '$2a$10$b38C6Bz41bFlDJPJBXuC8.ESB57HjTEqZHmlDR8FLD4VvJieRp86G');

/* INSERT INTO role_user TABLE */
INSERT INTO public.role_user(role, escapp_user)
	VALUES 
		('Admin', 3);

/* INSERT INTO comment TABLE */

INSERT INTO public.comment(id, text, target_type, id_comment_target, escapp_user)
	VALUES 
		(DEFAULT, 'Premier commentaire site1', 'Site', 1, 1),
		(DEFAULT, 'Premier commentaire secteur 1', 'Sector', 1, 2 ),
		(DEFAULT, '2nd commentaire site 1', 'Site', 1, 1);

/* INSERT INTO site TABLE */

INSERT INTO public.site(id, name, department, municipality, description)
	VALUES 
		(DEFAULT, 'Site 1', '034', 'Monpellier', 'Site 1 - Département de l''héraut - Ville de Monpellier'),
		(DEFAULT, 'Site 2', '030', 'Nimes', 'Site 2 - Département du gard - Ville de Nimes'),
		(DEFAULT, 'Site 3', '030', 'Uzès', 'Site 3 - Département du gard - Ville d''uzès');


/* INSERT INTO sector TABLE */

INSERT INTO public.sector(id, name, description, site_id)
	VALUES 
		(DEFAULT, 'Secteur 1 - Site 1', 'Premier secteur du site 1', 1),
		(DEFAULT, 'Secteur 2 - Site 1', 'Second secteur du site 1', 1),
		(DEFAULT, 'Secteur 1 - Site 3', 'Premier secteur du site 3', 3);

/* INSERT INTO route TABLE */

INSERT INTO public.route(id, sector_id, name, grade, points_nb, description)
	VALUES 
		(DEFAULT, 1, 'Route 1 - sect1 site1', '5a', 2, 'Route 1 - Secteur 1 - Site 1 - Grade 5a - 2 points'),
		(DEFAULT, 1, 'Route 2 - sect1 site1', '4a', 1, 'Route 2 - Secteur 1 - Site 1 - Grade 4a - 1 point'),
		(DEFAULT, 2, 'Route 1 - sect2 site1', '6b', 1, 'Route 1 - Secteur 2 - Site 1 - Grade 6b - 1 point'),
		(DEFAULT, 3, 'Route 1 - sect1 site3', '3c', 3, 'Route 1 - Secteur 1 - Site 3 - Grade 3c - 3 points');

/* INSERT INTO lenght TABLE */

INSERT INTO public.length(id, length, grade, points_nb, description, route_id)
	VALUES 
		(DEFAULT, 25, '5a', 1, 'Première longueur', 1),
		(DEFAULT, 20, '5a', 1, 'Seconde longueur', 1),
		(DEFAULT, 100, '4a', 1, 'Première longueur', 2),
		(DEFAULT, 50, '6b', 1, 'Première longueur', 3),
		(DEFAULT, 250, '3d', 2, 'Première longueur', 4),
		(DEFAULT, 75, '3b', 1, 'Seconde longueur', 4);


/* INSERT INTO topo TABLE */

INSERT INTO public.topo(id, name, department, is_borrowable, pdf_file_name, municipality, end_date_borrow, borrower_id, description, owner_id)
	VALUES 
		(DEFAULT, 'Topo 1 : Autour de Monpellier', '034', true, 'topo1_mtp.pdf', 'Monpellier', null, null, 'Topo 1 autour de montpellier dans le 34 (topo1_mtp.pdf), empruntable appartenant à user1', 1),
		(DEFAULT, 'Topo 2 : Autour de Nimes', '030', false, 'topo2_nimes.pdf', 'Nimes', null, null, 'Topo 2 autour de nimes dans le 30 (topo2_nimes.pdf, non empruntable appartenant à user1', 1),
		(DEFAULT, 'Topo 3 : topo générale', '034', true, 'topo3.pdf', null, null, null, 'Topo 3 générale sur le dep 34, empruntable appartenant à user1', 1),
		(DEFAULT, 'Topo 4 : topo Alès', '030', true, 'topo4_Ales.pdf', 'Alès', TIMESTAMP '2018-05-15 00:00:00', 1, 'emprunté par user 1 et appartenant à user2 - Fin location 15/05/2018', 2),
		(DEFAULT, 'Topo sans description', '048', false, 'topoSansDescription.pdf', 'Mende', null, null, null, 2);
