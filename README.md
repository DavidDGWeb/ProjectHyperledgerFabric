# PROYECTO BLOCKCHAIN HYPERLEDGER FABRIC
> El objetivo de este proyecto es desarrollar una **red Hyperledger Fabric que registre en la blockchain, las prendas fabricadas de marcas de lujo para evitar la venta de falsificaciones**. A si mismo los puntos de venta de las marcas al vender las prendas, pondrán asignar al propietario de la misma, que mediante un código qr dará la veracidad de la prenda y de su propiedad.    
>
> El chaincode también está diseñado para que desde un punto de venta, se pueda hacer la transferencia de la prenda a otro propietario, así como saber la lista de propietarios que ha tenido la prenda.
## Instalar componentes necesarios ##   
```- Docker & docker-compose```   
```- curl```     
```- jp```   
```- Java```     
```- IntelliJ IDEA```    
### Descargamos los binarios de Hyperledger Fabric y ejemplos de Docker ###   
```curl -sSLO https://raw.githubusercontent.com/hyperledger/fabric/main/scripts/install-fabric.sh && chmod +x install-fabric.sh```
### Instalamos Hyperledger Fabric ###   
```sudo ./install-fabric.sh docker samples binary```   
### Damos permisos sudo a la carpeta fabric-samples ###   
```sudo chmod -R 777 * fabric-samples```  
## Descargamos el chaincode y el API del repositorio GitHub ##   
### Alojamos la carpeta chaincode y gateway dentro de fabric-samples ###  
```fabric-samples/test-network/chaincode```  
```fabric-samples/test-network/gateway```  
## Desplegamos la red de Hyperledger Fabric con un servicio orderer, dos nodos peers, certificación Ca, BBDD CouchDB y un canal mychannel ##
```cd fabric-samples/test-network```   
```./network.sh up createChannel -ca -s couchdb```  
## Comprobamos que se despliega correctamente ##
```watch docker ps```  
## Deployamos el chaincode en la red ##   
```./network.sh deployCC -ccn chaincode -ccp ../chaincode/ -ccl java```  
## Inspeccionamos la BBDD con CounchDB ##   
```http://localhost:5984/_utils/#login```  
```User: admin | Pass: adminpw```  
## Explorador de la red para ver las métricas y los logs ##   
### Desplegamos Prometheus y Grafana ###   
```fabric-samples/test-network/prometheus-grafana```   
```*Ver el documento técnico de despliegue para configuración del docker-compose.yaml```  
```sudo docker-compose up -d```  
```Prometheus: http://localhost:9090/``` 
```Grafana: http://localhost:3000/login/```  
```User: admin  |  Pass: admin```  
## Instalamos y desplegamos Hyperledger Explorer ##   
### En la raíz creamos la carpeta explorer y descargamos lo archivos de instalación ###   
```sudo mkdir explorer```  
```sudo chmod -R 777 * explorer/```  
```cd explorer/```  
```wget https://raw.githubusercontent.com/hyperledger/blockchain-explorer/main/examples/net1/config.json```   
```wget https://raw.githubusercontent.com/hyperledger/blockchain-explorer/main/examples/net1/connection-profile/test-network.json -P connection-profile```   
```wget https://raw.githubusercontent.com/hyperledger/blockchain-explorer/main/docker-compose.yaml```  
### Copiamos los crypto artefactos de la red ###   
```sudo cp -r ~/fabric-samples/test-network/organizations/ ~/explorer/```  
```*Ver el documento técnico de despliegue para configuración del docker-compose.yaml``` 
### Desplegamos Hyperledger Explorer ###   
```sudo docker-compose up -d```  
```http://localhost:8080/#/login```  
```"User: exploreradmin  |  Pass: exploreradminpw'''   
```  ```    
## Para hacer los test en la red con el chaincode, a través del frontal Swagger, ver el documento técnico **[ProjectLuxuryClothing_DGWeb.pdf]**##   




