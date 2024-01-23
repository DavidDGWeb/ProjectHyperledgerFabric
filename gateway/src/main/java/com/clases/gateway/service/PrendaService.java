package com.clases.gateway.service;

import com.clases.gateway.dto.ResponseDTO;
import com.clases.gateway.utils.Constants;
import com.clases.gateway.repository.FabricGateway;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.hyperledger.fabric.client.Contract;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class PrendaService {

    public final FabricGateway fabricGateway;

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ResponseDTO registrarPrenda(final String id, final String marca, final String categoria, final String paisFabricacion, final String fechaFabricacion, final String propietario, final String qr) {

        ResponseDTO response = new ResponseDTO();

        try (var gateway = fabricGateway.createConnection().connect()) {
            var network = gateway.getNetwork(Constants.CHANNEL_NAME);

            // Get the smart contract from the network.
            Contract contract = network.getContract(Constants.CHAINCODE_PRENDA_NAME);
            byte[] result = contract.submitTransaction("registrarPrenda", id, marca, categoria, paisFabricacion, fechaFabricacion, propietario, qr);

            response.setCode("0");
            response.setData(prettyJson(result));
        } catch (Exception e) {
            response.setCode("1");
            response.setData(e.getMessage());
        }

        return response;
    }

    public ResponseDTO cargarPrenda(final String id) {

        ResponseDTO response = new ResponseDTO();

        try (var gateway = fabricGateway.createConnection().connect()) {
            var network = gateway.getNetwork(Constants.CHANNEL_NAME);

            // Get the smart contract from the network.
            Contract contract = network.getContract(Constants.CHAINCODE_PRENDA_NAME);
            byte[] result  = contract.submitTransaction("imprimirPrenda", id);

            response.setCode("0");
            response.setData(prettyJson(result));
        } catch (Exception e) {
            response.setCode("1");
            response.setData(e.getMessage());
        }

        return response;
    }

    public ResponseDTO borrarPrenda(final String id) {

        ResponseDTO response = new ResponseDTO();

        try (var gateway = fabricGateway.createConnection().connect()) {
            var network = gateway.getNetwork(Constants.CHANNEL_NAME);

            // Get the smart contract from the network.
            Contract contract = network.getContract(Constants.CHAINCODE_PRENDA_NAME);
            contract.submitTransaction("borrarPrenda", id);

            response.setCode("0");
            response.setData("Prenda Borrada");
        } catch (Exception e) {
            response.setCode("1");
            response.setData(e.getMessage());
        }

        return response;
    }

    public ResponseDTO transferenciaPrenda(final String id, final String newOwner) {

        ResponseDTO response = new ResponseDTO();

        try (var gateway = fabricGateway.createConnection().connect()) {
            var network = gateway.getNetwork(Constants.CHANNEL_NAME);

            // Get the smart contract from the network.
            Contract contract = network.getContract(Constants.CHAINCODE_PRENDA_NAME);
            byte[] result = contract.submitTransaction("transferenciaPrenda", id, newOwner);

            response.setCode("0");
            response.setData("Nuevo propietadio " + newOwner );
        } catch (Exception e) {
            response.setCode("1");
            response.setData(e.getMessage());
        }

        return response;
    }

    public ResponseDTO listarPrendas() {

        ResponseDTO response = new ResponseDTO();

        try (var gateway = fabricGateway.createConnection().connect()) {
            var network = gateway.getNetwork(Constants.CHANNEL_NAME);

            // Get the smart contract from the network.
            Contract contract = network.getContract(Constants.CHAINCODE_PRENDA_NAME);
            byte[] result  = contract.submitTransaction("listarPrendas");

            response.setCode("0");
            response.setData(prettyJson(result));
        } catch (Exception e) {
            response.setCode("1");
            response.setData(e.getMessage());
        }

        return response;
    }

    private String prettyJson(final byte[] json) {
        return prettyJson(new String(json, StandardCharsets.UTF_8));
    }

    private String prettyJson(final String json) {
        var parsedJson = JsonParser.parseString(json);
        return gson.toJson(parsedJson);
    }


}
