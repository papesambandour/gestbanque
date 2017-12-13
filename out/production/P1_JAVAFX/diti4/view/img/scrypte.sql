
alter table users add CONSTRAINT fkagence FOREIGN KEY(id_agence) REFERENCES agences(id) on DELETE SET NULL ON UPDATE CASCADE ;

alter table comptebanque add CONSTRAINT fkcompte FOREIGN KEY(idclient) REFERENCES client(id) on DELETE SET NULL ON UPDATE CASCADE ;

alter table agence_user_compte add CONSTRAINT fkag FOREIGN KEY(idagence) REFERENCES agences(id) on DELETE SET NULL ON UPDATE CASCADE ;

alter table agence_user_compte add CONSTRAINT fkuser FOREIGN KEY(iduser) REFERENCES users(id) on DELETE SET NULL ON UPDATE CASCADE ;

alter table agence_user_compte add CONSTRAINT fkcomp FOREIGN KEY(idagence) REFERENCES comptebanque(id) on DELETE SET NULL ON UPDATE CASCADE ;



