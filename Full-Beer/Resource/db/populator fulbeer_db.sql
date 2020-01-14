/*inserimento ruolo*/
insert into ruolo(tipo,username) values ('gestore_ordine','antonioga');
insert into ruolo(tipo,username) values ('gestore_catalogo','antonioga');
insert into ruolo(tipo,username) values ('utente','RaissaC');
/*inserimento prodotto*/
insert into prodotto(codice,prezzo,nome,descrizione,img,qt)values('002','3.50','Desrcos','Birra bionda forte doppio malto speziata, di alta fermentazione e rifermentata in bottiglia. ','birra2.jpg','10');
insert into prodotto(codice,prezzo,nome,descrizione,img,qt)values('003','2.60','Al-hambra','Birra Alhambra Reserve 1925 è una birra ispirata alle Strong Lager; è una birra chiara a fermentazione bassa.','birra3.jpg','10');
insert into prodotto(codice,prezzo,nome,descrizione,img,qt)values('004','4.70','Bavik','Una birra dal gusto leggero, liscia e maltata.','birra4.jpg','10');
insert into prodotto(codice,prezzo,nome,descrizione,img,qt)values('005','2.40','Camba','Una birra dal colore nero, dal corpo ben armonico, per niente amara','birra5.jpg','10');
insert into prodotto(codice,prezzo,nome,descrizione,img,qt)values('006','2.90','HoeolinGale',' Birra dal colore scuro e aroma di cioccolato, liquirizia e caffè','birra6.jpg','10');
insert into prodotto(codice,prezzo,nome,descrizione,img,qt)values('007','3.65','the-full','Full irish è una single malt, ossia prodotta con il 100% di malto irlandese, selezionando il miglior orzo della città di Cork.','birra7.jpg','10');
/*inserimento utente*/
insert into utente(nome, cognome, mail, password_, username)
values('Marco', 'Rossi', 'marco97@libero.it', 'marco8','marcoros');

insert into utente(nome, cognome, mail, password_, username)
values('Antonio', 'Gambale', 'antonio97@libero.it', 'antonio9','antonioga' );

insert into utente(nome, cognome, mail, password_, username)
values('Raissa', 'Colicino', 'raissacolicino@gmail.it', 'RaissaC','RaissaC' );

/*Inserimento indirizzo*/
insert into indirizzo(via, cap, citta, username)
values('Via Macchia 25', '83040', 'Montemarano', 'antonioga');

insert into indirizzo(via, cap, citta, username)
values('Via S.Francesco', '83048', 'Montella', 'RaissaC');

insert into indirizzo(via, cap, citta, username)
values('Via San Marco 28', '83048', 'Montella', 'marcoros');