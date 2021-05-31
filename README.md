# **Projecture**

Eksamens projekt for DAT20B - Gruppe 1: Databois

# Kørselsvejledning:

Du skal have følgende programmer før programmet kan køre lokalt på din enhed:

- IntelleJ Idea (eller tilsvarende IDE program)
- Seneste version af JAVA: https://www.java.com/en/download/windows_offline.jsp
- MySQL Workbench
- Adgang til webbrowser (Chrome eller andet)



****Trin 1:****
- Kør SQL script filen i MySQL workbench: src/main/resources/sql/script.sql
- Sørg for at application.properties password og bruger er korrekt (eller skal man rette det til ens eget)
  
****Trin 2:****
- ****VIGTIGT**** Indsæt følgende roller i role tabellen:

id/description/price
  
  - 1/Senior Developer/500.00
  - 2/Developer/350.00
  - 3/Designer/400.00


****Trin 3:**** 
- Clone repository og åben via Version control i din IDE. 
- Kør programmet (_hotkey: Shift + F10 hvis du kører IntelleJ_)

****Trin 4:**** 
- Åben ny webside: http://localhost:8080/
- Tryk "_Get Started_"
- Login med din bruger/register ny bruger

****Trin 5:**** 
- Opret nyt projekt under _"Create a new project"_ -> Giv projektet et navn og antal uger projektet forventes at køre
  - Tryk på _Create project_


****Trin 6:**** 
- Under projektet: 
  - Opret subtask til projektet under _"Add subtask"_ -> Giv subtasken et navn, ressourcer pr. udvikler (Senior Developer hours, Developer hours, Designer hours)
    - Tryk _Create subtask_
    

DEPLOYED LINK: 
http://projecture2-env.eba-ubvpjyhf.eu-north-1.elasticbeanstalk.com/

Youtube video med gennemgang af Webapplikationen: 
https://www.youtube.com/watch?v=5XKak_dr1ls&ab_channel=primarykofin

(Før at prisen kan udregnes skal du logge ud og ind igen, og finde det projekt du har oprettet og se prisen på subtask nu)

