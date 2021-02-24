package com.lepisori.voting.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.3.0.
 */
public class Ballot extends Contract {
    private static final String BINARY = "6080604052602060405190810160405280600060ff1681525060079060016200002a929190620001c2565b503480156200003857600080fd5b50604051620010b7380380620010b783398101806040528101908080518201929190602001805182019291906020018051906020019092919080518201929190505050600033600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000600160146101000a81548160ff0219169083151502179055508460009080519060200190620000f192919062000219565b50600090505b83518110156200018b576005604080519081016040528086848151811015156200011d57fe5b9060200190602002015160001916815260200160008152509080600181540180825580915050906001820390600052602060002090600202016000909192909190915060008201518160000190600019169055602082015181600101555050508080600101915050620000f7565b826002819055508160049080519060200190620001aa929190620002a0565b5060048054905060038190555050505050506200039d565b82805482825590600052602060002090810192821562000206579160200282015b8281111562000205578251829060ff16905591602001919060010190620001e3565b5b5090506200021591906200032f565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200025c57805160ff19168380011785556200028d565b828001600101855582156200028d579182015b828111156200028c5782518255916020019190600101906200026f565b5b5090506200029c91906200032f565b5090565b8280548282559060005260206000209081019282156200031c579160200282015b828111156200031b5782518260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555091602001919060010190620002c1565b5b5090506200032b919062000357565b5090565b6200035491905b808211156200035057600081600090555060010162000336565b5090565b90565b6200039a91905b808211156200039657600081816101000a81549073ffffffffffffffffffffffffffffffffffffffff0219169055506001016200035e565b5090565b90565b610d0a80620003ad6000396000f3006080604052600436106100af576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630121b93f146100b4578063013cf08b146100e157806306fdde03146101315780630d15fd77146101c157806320289077146101ec578063597e1fb5146102175780635b4246d41461024657806387f7b97514610271578063a7b49529146102c8578063a97b8b4d1461030b578063e2ba53f014610322575b600080fd5b3480156100c057600080fd5b506100df6004803603810190808035906020019092919050505061038e565b005b3480156100ed57600080fd5b5061010c600480360381019080803590602001909291905050506106f4565b6040518083600019166000191681526020018281526020019250505060405180910390f35b34801561013d57600080fd5b50610146610727565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561018657808201518184015260208101905061016b565b50505050905090810190601f1680156101b35780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156101cd57600080fd5b506101d66107c5565b6040518082815260200191505060405180910390f35b3480156101f857600080fd5b506102016107cb565b6040518082815260200191505060405180910390f35b34801561022357600080fd5b5061022c6107d8565b604051808215151515815260200191505060405180910390f35b34801561025257600080fd5b5061025b6107eb565b6040518082815260200191505060405180910390f35b34801561027d57600080fd5b506102866107f1565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156102d457600080fd5b50610309600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610817565b005b34801561031757600080fd5b5061032061085b565b005b34801561032e57600080fd5b50610337610962565b6040518080602001828103825283818151815260200191508051906020019060200280838360005b8381101561037a57808201518184015260208101905061035f565b505050509050019250505060405180910390f35b6000806000339250600160149054906101000a900460ff1615151561041b576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601d8152602001807f7468652062616c6c6f7420697320616c726561647920636c6f7365642e00000081525060200191505060405180910390fd5b60009150600090505b6004805490508110156104b4578273ffffffffffffffffffffffffffffffffffffffff1660048281548110151561045757fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156104a757600191506104b4565b8080600101915050610424565b811515610529576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260168152602001807f74686520766f74657220697320696c6c656167616c210000000000000000000081525060200191505060405180910390fd5b600580549050841015156105a5576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260168152602001807f7468652070726f73616c20697320696e76616c6964210000000000000000000081525060200191505060405180910390fd5b600660008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff16151515610667576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600e8152602001807f416c726561647920766f7465642e00000000000000000000000000000000000081525060200191505060405180910390fd5b6001600660008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff02191690831515021790555060016005858154811015156106d057fe5b90600052602060002090600202016001016000828254019250508190555050505050565b60058181548110151561070357fe5b90600052602060002090600202016000915090508060000154908060010154905082565b60008054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107bd5780601f10610792576101008083540402835291602001916107bd565b820191906000526020600020905b8154815290600101906020018083116107a057829003601f168201915b505050505081565b60035481565b6000600580549050905090565b600160149054906101000a900460ff1681565b60025481565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b80600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b3373ffffffffffffffffffffffffffffffffffffffff16600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141515610946576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260278152602001807f6f6e6c79207468652063686169726d616e2063616e20636c6f7365207468652081526020017f62616c6c6f742e0000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b60018060146101000a81548160ff021916908315150217905550565b60606000600160149054906101000a900460ff161515610a10576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260398152602001807f6f6e6c79207768656e207468652062616c6c6f7420697320636c6f736564206381526020017f616e20796f75206578616d696e65207468652077696e6e65720000000000000081525060400191505060405180910390fd5b610a18610afd565b600090505b600780549050811015610aa25760086005600783815481101515610a3d57fe5b9060005260206000200154815481101515610a5457fe5b90600052602060002090600202016000015490806001815401808255809150509060018203906000526020600020016000909192909190915090600019169055508080600101915050610a1d565b6008805480602002602001604051908101604052809291908181526020018280548015610af257602002820191906000526020600020905b81546000191681526020019060010190808311610ada575b505050505091505090565b6000806000600781610b0f9190610c8d565b5060009150600090505b600580549050811015610b9b576002546003546064600584815481101515610b3d57fe5b90600052602060002090600202016001015402811515610b5957fe5b04101515610b8e5760078190806001815401808255809150509060018203906000526020600020016000909192909190915055505b8080600101915050610b19565b60006007805490501415610c8957600090505b600580549050811015610c145781600582815481101515610bcb57fe5b9060005260206000209060020201600101541115610c0757600581815481101515610bf257fe5b90600052602060002090600202016001015491505b8080600101915050610bae565b600090505b600580549050811015610c885781600582815481101515610c3657fe5b9060005260206000209060020201600101541415610c7b5760078190806001815401808255809150509060018203906000526020600020016000909192909190915055505b8080600101915050610c19565b5b5050565b815481835581811115610cb457818360005260206000209182019101610cb39190610cb9565b5b505050565b610cdb91905b80821115610cd7576000816000905550600101610cbf565b5090565b905600a165627a7a72305820862fdf8cf4cc3670b259c16ce7ea4c8656e986de7f38fd001f0740ee46e788790029";

    public static final String FUNC_VOTE = "vote";

    public static final String FUNC_PROPOSALS = "proposals";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_TOTALVOTES = "totalVotes";

    public static final String FUNC_GETPROPOSALNUMBER = "getProposalNumber";

    public static final String FUNC_CLOSED = "closed";

    public static final String FUNC_PROPORTION = "proportion";

    public static final String FUNC_CHAIRMAN = "chairman";

    public static final String FUNC_SETCHAIRMAN = "setChairman";

    public static final String FUNC_CLOSEBALLOT = "closeBallot";

    public static final String FUNC_WINNERNAME = "winnerName";

    @Deprecated
    protected Ballot(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Ballot(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Ballot(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Ballot(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> vote(Uint256 proposal) {
        final Function function = new Function(
                FUNC_VOTE, 
                Arrays.<Type>asList(proposal), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple2<Bytes32, Uint256>> proposals(Uint256 param0) {
        final Function function = new Function(FUNC_PROPOSALS, 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple2<Bytes32, Uint256>>(
                new Callable<Tuple2<Bytes32, Uint256>>() {
                    @Override
                    public Tuple2<Bytes32, Uint256> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<Bytes32, Uint256>(
                                (Bytes32) results.get(0), 
                                (Uint256) results.get(1));
                    }
                });
    }

    public RemoteCall<Utf8String> name() {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> totalVotes() {
        final Function function = new Function(FUNC_TOTALVOTES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> getProposalNumber() {
        final Function function = new Function(FUNC_GETPROPOSALNUMBER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Bool> closed() {
        final Function function = new Function(FUNC_CLOSED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> proportion() {
        final Function function = new Function(FUNC_PROPORTION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Address> chairman() {
        final Function function = new Function(FUNC_CHAIRMAN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> setChairman(Address _chairman) {
        final Function function = new Function(
                FUNC_SETCHAIRMAN, 
                Arrays.<Type>asList(_chairman), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> closeBallot() {
        final Function function = new Function(
                FUNC_CLOSEBALLOT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<DynamicArray<Bytes32>> winnerName() {
        final Function function = new Function(FUNC_WINNERNAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Bytes32>>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    @Deprecated
    public static Ballot load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Ballot(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Ballot load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Ballot(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Ballot load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Ballot(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Ballot load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Ballot(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Ballot> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, Utf8String _name, DynamicArray<Bytes32> _proposals, Uint256 _proportion, DynamicArray<Address> _voters) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_name, _proposals, _proportion, _voters));
        return deployRemoteCall(Ballot.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Ballot> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, Utf8String _name, DynamicArray<Bytes32> _proposals, Uint256 _proportion, DynamicArray<Address> _voters) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_name, _proposals, _proportion, _voters));
        return deployRemoteCall(Ballot.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Ballot> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, Utf8String _name, DynamicArray<Bytes32> _proposals, Uint256 _proportion, DynamicArray<Address> _voters) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_name, _proposals, _proportion, _voters));
        return deployRemoteCall(Ballot.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Ballot> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, Utf8String _name, DynamicArray<Bytes32> _proposals, Uint256 _proportion, DynamicArray<Address> _voters) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_name, _proposals, _proportion, _voters));
        return deployRemoteCall(Ballot.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
