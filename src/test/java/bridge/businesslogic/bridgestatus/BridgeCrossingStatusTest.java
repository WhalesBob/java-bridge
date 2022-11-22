package bridge.businesslogic.bridgestatus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BridgeCrossingStatusTest {

    private BridgeCrossingStatus bridgeCrossing;

    @Nested
    class UpdateStatusTest{

        @DisplayName("아무것도 없을 때 위쪽을 선택하면, 다리 위에 상태에 O 가 들어간다.")
        @Test
        void updateStatus_case1() {
            StringBuilder upLine = new StringBuilder();
            StringBuilder downLine = new StringBuilder();
            bridgeCrossing = new BridgeCrossingStatus(upLine,downLine);
            bridgeCrossing.updateStatus("U");
            assertThat(upLine.toString()).isEqualTo("O");
            assertThat(downLine.toString()).isEqualTo(" ");
        }

        @DisplayName("아무것도 없을 때 아래쪽을 선택하면, 다리 아래 상태에 O 가 들어간다.")
        @Test
        void updateStatus_case2() {
            StringBuilder upLine = new StringBuilder();
            StringBuilder downLine = new StringBuilder();
            bridgeCrossing = new BridgeCrossingStatus(upLine,downLine);
            bridgeCrossing.updateStatus("D");
            assertThat(upLine.toString()).isEqualTo(" ");
            assertThat(downLine.toString()).isEqualTo("O");
        }

        @DisplayName("이미 데이터가 있는 상태에서 위를 선택하면, 다리 위 상태에 | O 가 들어간다.")
        @Test
        void updateStatus_case3() {
            StringBuilder upLine = new StringBuilder("O");
            StringBuilder downLine = new StringBuilder(" ");
            bridgeCrossing = new BridgeCrossingStatus(upLine,downLine);
            bridgeCrossing.updateStatus("U");
            assertThat(upLine.toString()).isEqualTo("O | O");
            assertThat(downLine.toString()).isEqualTo("  |  ");
        }

        @DisplayName("이미 데이터가 있는 상태에서 아래를 선택하면, 다리 아래 상태에 | O 가 들어간다.")
        @Test
        void updateStatus_case4() {
            StringBuilder upLine = new StringBuilder("O");
            StringBuilder downLine = new StringBuilder(" ");
            bridgeCrossing = new BridgeCrossingStatus(upLine,downLine);
            bridgeCrossing.updateStatus("D");
            assertThat(upLine.toString()).isEqualTo("O |  ");
            assertThat(downLine.toString()).isEqualTo("  | O");
        }
    }

    @Nested
    class ClearAllTest{

        @DisplayName("데이터가 없을 때는, clearAll 해도 그냥 데이터가 없는 상태이다.")
        @Test
        void clearAll_case1(){
            StringBuilder upLine = new StringBuilder();
            StringBuilder downLine = new StringBuilder();
            bridgeCrossing = new BridgeCrossingStatus(upLine,downLine);
            bridgeCrossing.clearAll();
            assertThat(upLine.toString()).isEqualTo("");
            assertThat(downLine.toString()).isEqualTo("");
        }

        @DisplayName("데이터가 있을 때, clearAll 하면 두 라인의 데이터 모두가 지워진다.")
        @Test
        void clearAll_case2(){
            StringBuilder upLine = new StringBuilder("O |   | O");
            StringBuilder downLine = new StringBuilder("  | O |  ");
            bridgeCrossing = new BridgeCrossingStatus(upLine,downLine);
            bridgeCrossing.clearAll();
            assertThat(upLine.toString()).isEqualTo("");
            assertThat(downLine.toString()).isEqualTo("");
        }
    }

}