package org.tron.core.vm;

import org.tron.common.runtime.InternalTransaction;
import org.tron.common.runtime.ProgramResult;
import org.tron.common.runtime.vm.DataWord;
import org.tron.core.exception.ContractValidateException;
import org.tron.core.store.StoreFactory;
import org.tron.core.vm.config.VMConfig;
import org.tron.core.vm.program.Program;
import org.tron.core.vm.program.invoke.ProgramInvoke;
import org.tron.core.vm.program.invoke.ProgramInvokeFactory;
import org.tron.core.vm.program.invoke.ProgramInvokeMockImpl;
import org.tron.core.vm.repository.RepositoryImpl;
import org.tron.protos.Protocol;

/**
 * @author xiaochangbai
 * @date 2025-04-01 21:01
 */
public class TVMDemo {


    public static void main(String[] args) throws ContractValidateException {

//        byte[] op = {0x5b, 0x60, 0x00, 0x56};
        VMConfig.initAllowTvmShangHai(1);
        String a = "6080604052348015600e575f5ffd5b506101528061001c5f395ff3fe608060405234801561000f575f5ffd5b5060043610610029575f3560e01c8063d0679d341461002d575b5f5ffd5b610047600480360381019061004291906100de565b610049565b005b5050565b5f5ffd5b5f73ffffffffffffffffffffffffffffffffffffffff82169050919050565b5f61007a82610051565b9050919050565b61008a81610070565b8114610094575f5ffd5b50565b5f813590506100a581610081565b92915050565b5f819050919050565b6100bd816100ab565b81146100c7575f5ffd5b50565b5f813590506100d8816100b4565b92915050565b5f5f604083850312156100f4576100f361004d565b5b5f61010185828601610097565b9250506020610112858286016100ca565b915050925092905056fea2646970667358221220dc63a59f02368eae65c27161981b0e092cfbbe23abcaf19876e8b97d92fe0d3a64736f6c634300081b0033";
       byte[] op = org.tron.common.utils.ByteUtil.hexToBytes(a);
        //        byte[] op = {Op.JUMPDEST, Op.PUSH2, (byte) 0xfe,0x02,Op.PUSH1,0x01, Op.ADD,0x00};
        // 0x5b      - JUMPTEST 跳过当前这一步，进行下一步
        // 0x60 0x00 - PUSH 0x00   把0x00压入栈中
        // 0x60 0x01 - PUSH 0x00   把0x01压入栈中
        // 0x01      - ADD 0x01   ADD
        // 0x00      - STOP   停止
        Protocol.Transaction trx = Protocol.Transaction.getDefaultInstance();
        InternalTransaction interTrx = new InternalTransaction(trx, InternalTransaction.TrxType.TRX_UNKNOWN_TYPE);
        ProgramInvokeMockImpl invoke = new ProgramInvokeMockImpl(op, op);
        Program program = new Program(op, op, invoke, interTrx);

        VM.play(program, OperationRegistry.getTable());
        ProgramResult result = program.getResult();
        System.out.println(result.getResultCode());
        System.out.println(result.getException());
    }

}
