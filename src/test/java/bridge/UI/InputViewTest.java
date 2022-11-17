package bridge.UI;


import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

class InputViewTest {

    private static final String ERROR_MESSAGE = "[ERROR]";

    OutputStream out;
    InputStream in;
    InputView inputView = new InputView();

    void beforeSetting(String input){
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    void getExceptionTest(String input, Supplier<?> testFunction){
        beforeSetting(input);
        try{
            testFunction.get();
        }catch(NoSuchElementException ignored){}
        assertThat(out.toString().trim()).contains(ERROR_MESSAGE);
    }

    @Nested
    class ReadBridgeSizeTest{

        Supplier<Integer> functionSupply = () -> inputView.readBridgeSize();

        void getBridgeSizeTest(String input){
            beforeSetting(input);
            int output = Integer.parseInt(input);
            assertThat(inputView.readBridgeSize()).isEqualTo(output);
        }

        @Test
        void readBridgeSize_case1(){
            String input = "10";
            getBridgeSizeTest(input);
        }

        @Test
        void readBridgeSize_case2(){
            String input = "3";
            getBridgeSizeTest(input);
        }

        @Test
        void readBridgeSize_case3(){
            String input = "20";
            getBridgeSizeTest(input);
        }

        @Test
        void readBridgeSize_exceptionCase1(){
            String input = "1";
            getExceptionTest(input,functionSupply);
        }

        @Test
        void readBridgeSize_exceptionCase2(){
            String input = "100";
            getExceptionTest(input,functionSupply);
        }

        @Test
        void readBridgeSize_exceptionCase3(){
            String input = "-5";
            getExceptionTest(input,functionSupply);
        }

        @Test
        void readBridgeSize_exceptionCase4(){
            String input = "0";
            getExceptionTest(input,functionSupply);
        }

        @Test
        void readBridgeSize_exceptionCase5(){
            String input = "Hello World";
            getExceptionTest(input,functionSupply);
        }

        @Test
        void readBridgeSize_exceptionCase6(){
            String input = "!@#!$";
            getExceptionTest(input,functionSupply);
        }

        @Test
        void readBridgeSize_exceptionCase7(){
            String input = " ";
            getExceptionTest(input,functionSupply);
        }
    }

    @Nested
    class ReadMovingTest{

        Supplier<String> functionSupply = () -> inputView.readMoving();

        void getReadMovingTest(String input){
            beforeSetting(input);
            assertThat(inputView.readMoving()).isEqualTo(input);
        }

        @Test
        void readMoving_case1(){
            String input = "U";
            getReadMovingTest(input);
        }

        @Test
        void readMoving_case2(){
            String input = "D";
            getReadMovingTest(input);
        }

        @Test
        void readMoving_exceptionCase1(){
            String input = "UD";
            getExceptionTest(input,functionSupply);
        }

        @Test
        void readMoving_exceptionCase2(){
            String input = "-1";
            getExceptionTest(input,functionSupply);
        }

        @Test
        void readMoving_exceptionCase3(){
            String input = "10000";
            getExceptionTest(input,functionSupply);
        }

        @Test
        void readMoving_exceptionCase4(){
            String input = "A";
            getExceptionTest(input,functionSupply);
        }

        @Test
        void readMoving_exceptionCase5(){
            String input = "Z";
            getExceptionTest(input,functionSupply);
        }

        @Test
        void readMoving_exceptionCase6(){
            String input = "Hello";
            getExceptionTest(input,functionSupply);
        }

        @Test
        void readMoving_exceptionCase7(){
            String input = "!!!";
            getExceptionTest(input,functionSupply);
        }

        @Test
        void readMoving_exceptionCase8(){
            String input = " ";
            getExceptionTest(input,functionSupply);
        }
    }

    @Nested
    class ReadGameCommandTest{

        Supplier<String> functionSupply = () -> inputView.readGameCommand();

        void getGameCommandTest(String input){
            beforeSetting(input);
            assertThat(inputView.readGameCommand()).isEqualTo(input);
        }

        @Test
        void readGameCommand_case1(){
            String input = "R";
            getGameCommandTest(input);
        }

        @Test
        void readGameCommand_case2(){
            String input = "Q";
            getGameCommandTest(input);
        }

        @Test
        void readGameCommand_exceptionCase1(){
            String input = "RQ";
            getExceptionTest(input,functionSupply);
        }

        @Test
        void readGameCommand_exceptionCase2(){
            String input = "A";
            getExceptionTest(input,functionSupply);
        }

        @Test
        void readGameCommand_exceptionCase3(){
            String input = "X";
            getExceptionTest(input,functionSupply);
        }

        @Test
        void readGameCommand_exceptionCase4(){
            String input = "Hi";
            getExceptionTest(input,functionSupply);
        }

        @Test
        void readGameCommand_exceptionCase5(){
            String input = " ";
            getExceptionTest(input,functionSupply);
        }

        @Test
        void readGameCommand_exceptionCase6(){
            String input = "!@#";
            getExceptionTest(input,functionSupply);
        }

        @Test
        void readGameCommand_exceptionCase7(){
            String input = "5";
            getExceptionTest(input,functionSupply);
        }

        @Test
        void readGameCommand_exceptionCase8(){
            String input = "-1234";
            getExceptionTest(input,functionSupply);
        }
    }
}