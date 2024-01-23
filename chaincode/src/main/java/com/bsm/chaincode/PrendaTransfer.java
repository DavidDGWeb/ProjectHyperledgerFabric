package com.bsm.chaincode;

import com.owlike.genson.Genson;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Info;
import org.hyperledger.fabric.contract.annotation.Transaction;
import org.hyperledger.fabric.shim.ChaincodeException;
import org.hyperledger.fabric.shim.ChaincodeStub;
import org.hyperledger.fabric.shim.ledger.KeyValue;
import org.hyperledger.fabric.shim.ledger.QueryResultsIterator;

import java.util.ArrayList;
import java.util.List;

@Contract(
        name = "PrendaTransfer",
        info = @Info(
                title = "PrendaTransfer contract",
                description = "Chaincode para el registro de fabricación de prendas de lujo",
                version = "0.0.1"))
@Default
public final class PrendaTransfer implements ContractInterface {

    private final Genson genson = new Genson();

    private enum PrendaTransferErrors {
        Prenda_NOT_FOUND,
        Prenda_ALREADY_EXISTS
    }

    @Transaction(intent = Transaction.TYPE.SUBMIT)
    public AssetPrenda registrarPrenda(final Context ctx, final String id, final String marca, final String categoria, final String paisFabricacion, final String fechaFabricacion, final String owner, final String qr) {

        ChaincodeStub stub = ctx.getStub();

        String state = stub.getStringState(id);

        if (!state.isEmpty()) {
            String errorMessage = String.format("Prenda ya registrada", id);
            System.out.println(errorMessage);
            throw new ChaincodeException(errorMessage, PrendaTransferErrors.Prenda_ALREADY_EXISTS.toString());
        }

        List<String> propietarios = new ArrayList<>();
        propietarios.add(owner);

        AssetPrenda Prenda = new AssetPrenda(id, marca, categoria, paisFabricacion, fechaFabricacion, owner, qr, propietarios);

        String newState = genson.serialize(Prenda);

        stub.putStringState(id, newState);

        return Prenda;
    }

    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public AssetPrenda imprimirPrenda(final Context ctx, final String id) {
        ChaincodeStub stub = ctx.getStub();
        String state = stub.getStringState(id);

        if (state.isEmpty() || state == null) {
            String errorMessage = String.format("Prenda no registrada", id);
            System.out.println(errorMessage);
            throw new ChaincodeException(errorMessage, PrendaTransferErrors.Prenda_NOT_FOUND.toString());
        }

        AssetPrenda Prenda = genson.deserialize(state, AssetPrenda.class);
        return Prenda;
    }

    @Transaction(intent = Transaction.TYPE.SUBMIT)
    public void borrarPrenda(final Context ctx, final String id) {
        ChaincodeStub stub = ctx.getStub();

        String state = stub.getStringState(id);

        if (state.isEmpty() || state == null) {
            String errorMessage = String.format("Prenda no registrada", id);
            System.out.println(errorMessage);
            throw new ChaincodeException(errorMessage, PrendaTransferErrors.Prenda_NOT_FOUND.toString());
        }

        stub.delState(id);
    }

    @Transaction(intent = Transaction.TYPE.SUBMIT)
    public String transferenciaPrenda(final Context ctx, final String id, final String newOwner) {
        ChaincodeStub stub = ctx.getStub();
        String state = stub.getStringState(id);

        if (state == null || state.isEmpty()) {
            String errorMessage = String.format("Prenda no registrada", id);
            System.out.println(errorMessage);
            throw new ChaincodeException(errorMessage, PrendaTransferErrors.Prenda_NOT_FOUND.toString());
        }

        AssetPrenda asset = genson.deserialize(state, AssetPrenda.class);
        List<String> listapropietarios = asset.getpropietarios();
        listapropietarios.add(newOwner);

        AssetPrenda newAsset = new AssetPrenda(asset.getId(), asset.getmarca(), asset.getcategoria(), asset.getpaisFabricacion(), asset.getfechaFabricacion(), newOwner , asset.getqr(), asset.getpropietarios());
        String sortedJson = genson.serialize(newAsset);
        stub.putStringState(id, sortedJson);

        return "Nuevo propietario: " + newOwner;
    }

    @Transaction(intent = Transaction.TYPE.EVALUATE)
    public String listarPrendas(final Context ctx) {
        ChaincodeStub stub = ctx.getStub();

        List<AssetPrenda> queryResults = new ArrayList<AssetPrenda>();
        QueryResultsIterator<KeyValue> results = stub.getStateByRange("", "");

        for (KeyValue result: results) {
            AssetPrenda asset = genson.deserialize(result.getStringValue(), AssetPrenda.class);
            System.out.println(asset);
            queryResults.add(asset);
        }

        final String response = genson.serialize(queryResults);

        return response;
    }





}
