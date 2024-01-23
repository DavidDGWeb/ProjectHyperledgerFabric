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
public class BasicService {


    public final FabricGateway fabricGateway;

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ResponseDTO createAsset(final String assetID, final String color, final int size,
                                   final String owner, final int appraisedValue) {

        ResponseDTO response = new ResponseDTO();

        try (var gateway = fabricGateway.createConnection().connect()) {
                var network = gateway.getNetwork(Constants.CHANNEL_NAME);

                // Get the smart contract from the network.
                Contract contract = network.getContract(Constants.CHAINCODE_BASIC_NAME);
                byte[] result = contract.submitTransaction("CreateAsset", assetID, color, String.valueOf(size), owner, String.valueOf(appraisedValue));

                response.setCode("0");
                response.setData(prettyJson(result));
        } catch (Exception e) {
            response.setCode("1");
            response.setData(e.getMessage());
        }

        return response;
    }

    public ResponseDTO readAsset(final String assetID) {

        ResponseDTO response = new ResponseDTO();

        try (var gateway = fabricGateway.createConnection().connect()) {
            var network = gateway.getNetwork(Constants.CHANNEL_NAME);

            // Get the smart contract from the network.
            Contract contract = network.getContract(Constants.CHAINCODE_BASIC_NAME);
            byte[] result  = contract.submitTransaction("ReadAsset", assetID);

            response.setCode("0");
            response.setData(prettyJson(result));
        } catch (Exception e) {
            response.setCode("1");
            response.setData(e.getMessage());
        }

        return response;
    }

    public ResponseDTO updateAsset(final String assetID, final String color, final int size,
                                   final String owner, final int appraisedValue) {

        ResponseDTO response = new ResponseDTO();

        try (var gateway = fabricGateway.createConnection().connect()) {
            var network = gateway.getNetwork(Constants.CHANNEL_NAME);

            // Get the smart contract from the network.
            Contract contract = network.getContract(Constants.CHAINCODE_BASIC_NAME);
            byte[] result = contract.submitTransaction("UpdateAsset", assetID, color, String.valueOf(size), owner, String.valueOf(appraisedValue));

            response.setCode("0");
            response.setData(prettyJson(result));
        } catch (Exception e) {
            response.setCode("1");
            response.setData(e.getMessage());
        }

        return response;
    }

    public ResponseDTO deleteAsset(final String assetID) {

        ResponseDTO response = new ResponseDTO();

        try (var gateway = fabricGateway.createConnection().connect()) {
            var network = gateway.getNetwork(Constants.CHANNEL_NAME);

            // Get the smart contract from the network.
            Contract contract = network.getContract(Constants.CHAINCODE_BASIC_NAME);
            contract.submitTransaction("DeleteAsset", assetID);

            response.setCode("0");
            response.setData("Asset Borrado");
        } catch (Exception e) {
            response.setCode("1");
            response.setData(e.getMessage());
        }

        return response;
    }

    public ResponseDTO assetExists(final String assetID) {

        ResponseDTO response = new ResponseDTO();

        try (var gateway = fabricGateway.createConnection().connect()) {
            var network = gateway.getNetwork(Constants.CHANNEL_NAME);

            // Get the smart contract from the network.
            Contract contract = network.getContract(Constants.CHAINCODE_BASIC_NAME);
            byte[] result  = contract.submitTransaction("AssetExists", assetID);

            response.setCode("0");
            response.setData(prettyJson(result));
        } catch (Exception e) {
            response.setCode("1");
            response.setData(e.getMessage());
        }

        return response;
    }

    public ResponseDTO transferAsset(final String assetID, final String newOwner) {

        ResponseDTO response = new ResponseDTO();

        try (var gateway = fabricGateway.createConnection().connect()) {
            var network = gateway.getNetwork(Constants.CHANNEL_NAME);

            // Get the smart contract from the network.
            Contract contract = network.getContract(Constants.CHAINCODE_BASIC_NAME);
            byte[] result = contract.submitTransaction("TransferAsset", assetID, newOwner);

            response.setCode("0");
            response.setData("New owner " + newOwner);
        } catch (Exception e) {
            response.setCode("1");
            response.setData(e.getMessage());
        }

        return response;
    }

    public ResponseDTO getAllAssets() {

        ResponseDTO response = new ResponseDTO();

        try (var gateway = fabricGateway.createConnection().connect()) {
            var network = gateway.getNetwork(Constants.CHANNEL_NAME);

            // Get the smart contract from the network.
            Contract contract = network.getContract(Constants.CHAINCODE_BASIC_NAME);
            byte[] result  = contract.submitTransaction("GetAllAssets");

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
