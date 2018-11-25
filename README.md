# wheretoclimb
A website to find where you can climb an borrow and / or loan atlas of the areas or crags' routes you want to climb

Deployment:
1 - Create a Postgresql database named db_wtc 
	- datasource URL: jdbc:postgresql://localhost:5432/db_wtc 
	- owner ID: admin_wtc 
	- password: 123

2 - Restore the database with file: WhereToClimb/db/db_wtc.backup
	- don't forget the Restore options>Sections> Pre-data, Data and Post-data must be checked

3 - Please verify that you have Java 8 (at least) installed on your device.

4 - Go to the directory: WhereToClimb/webapp/output

5 - Either double-clic on the webapp.jar file or run it from console with the command: java -jar webapp.jar

6 - In a browser, open: http://localhost:8080/public/showAtlas


Use the App:
You have an admin account
ID: Admin  PWD: Abcde3@

You have 2 user accounts
ID: TheJohn  PWD: Abcde1@
ID: Lele  PWD: Abcde2@


