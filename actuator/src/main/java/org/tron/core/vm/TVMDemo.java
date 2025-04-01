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
        byte[] op = {0x5b, 0x60, 0x00, 0x56};
        // 0x5b      - JUMPTEST 跳过当前这一步，进行下一步
        // 0x60 0x00 - PUSH 0x00   把0x00压入栈中
        // 0x56      - JUMP to 0   跳转到第0步骤
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
