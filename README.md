# PROYECTO BLOCKCHAIN HYPERLEDGER FABRIC
## Instalar componentes necesarios ##   
### - Docker & docker-compose ###   
### - curl ###   
### - jp ###
### - Java ###   
### - IntelliJ IDEA ###   
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
