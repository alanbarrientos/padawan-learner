
c:
cd C:\Users\alanb\DEV\dietapp

cd backend
rem borrar el contenido de la carpeta static
call rmdir "backend\src\main\resources\static\" /s /q
call mkdir backend\src\main\resources\static
call mvn clean
cd "../frontend"
call npm run build --skip-tests 
cd ../backend
call mvn -DskipTests=true package
cd target 
call scp springboot-dieta-0.0.1-SNAPSHOT.jar root@143.244.163.204:~/springboot-dieta-ang.jar
call ssh root@143.244.163.204 "nohup java -jar springboot-dieta-ang.jar 2>/dev/null >/dev/null &"
     