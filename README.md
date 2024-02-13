In order to open our project to your machine you should click the green button name "Code" download the ZIP file. Then you should extract the file to the current diractory on which you would like the project to be. We have to inform you that we use a dockerized postgress DataBase so run the following command to your project terminal in intellij IDE while the Docker app is running: docker run --name ds-lab-pg --rm
-p 5432:5432
-e POSTGRES_USER=dbuser
-e POSTGRES_DB=appdb
-v ds-lab-vol:/var/lib/postgresql/data
postgres:14

NOTE: if you want to keep the container after you close the project just remove --rm
