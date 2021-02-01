import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class EnumTest {

    @Test
    public void FileWriteModeTest() {
        int given = 0;

        assertTrue(FileWriteMode.DIRECT_FILE_WRITE.equals(0));
        assertTrue(FileWriteMode.DIRECT_FILE_WRITE.name().equals("DIRECT_FILE_WRITE"));
    }

    @Test
    public void RealGeneratorServerTest() {
        assertTrue(RealGeneratorServer.D1.getHostNames().contains("11DEPLOYb-statR01.br.skp"));
        assertFalse(RealGeneratorServer.D2.getHostNames().contains("11DEPLOYb-statR01.br.skp"));
        assertTrue(RealGeneratorServer.D2.getHostNames().contains("tmbatch1"));
    }


    @Test
    public void nameNotFoundTest() {
        RealGeneratorServer realGeneratorServer;

        try {
            realGeneratorServer = RealGeneratorServer.valueOf("aa");
        } catch (IllegalArgumentException e) {
            realGeneratorServer = RealGeneratorServer.D2;
        }

        assertEquals(realGeneratorServer, RealGeneratorServer.D2);


    }

    @Test
    public void serverZoneTest() {
        assertEquals(ServerZone.findByHostName("vfrontbatch01"), ServerZone.D2D3);
        assertEquals(ServerZone.findByHostName("aa"), ServerZone.DEV);

        assertEquals(ServerZone.findSlackChannelByHostName("vfrontbatch01")
                , ServerZone.D2D3.slackChannel);

    }

    private enum FileWriteMode {
        DIRECT_FILE_WRITE(0),
        TEMPORARY_FILE_RENAME(1),
        TEMPORARY_FILE_COPY(2);

        private final int modeNumber;

        FileWriteMode (int modeNumber) {
            this.modeNumber = modeNumber;
        }

		public boolean equals(int modeNumber) {
			return this.modeNumber == modeNumber;
		}
    }


    private enum RealGeneratorServer {
        D1("#log_htmlgen_alarm_D1") {
            @Override
            public Set<String> getHostNames() {
                Set<String> hostNames = new HashSet<>();

                hostNames.add("11DEPLOYb-statR01.br.skp");
                return hostNames;
            }
        },
        D2("#log_htmlgen_alarm") {
            @Override
            public Set<String> getHostNames() {
                Set<String> hostNames = new HashSet<>();

                hostNames.add("vfrontbatch01");
                hostNames.add("tmbatch1");
                return hostNames;
            }
        },
        DEV("#log_htmlgen_alarm_dev") {
            @Override
            public Set<String> getHostNames() {
                return null;
            }
        };

        private final String slackChannel;

        RealGeneratorServer(String slackChannel) {
            this.slackChannel = slackChannel;
        }

        abstract public Set<String> getHostNames();
    }

    @Test
    public void errorTest() {
        ServerZone serverZone;

        try {
            serverZone = ServerZone.valueOf("aaa");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        serverZone = ServerZone.get("aa");
        assertEquals(Objects.isNull(serverZone), true);

        if ( Objects.isNull(serverZone) ) {

        }
    }

    @Test
    public void isAllTest () {
        assertEquals(TestEnum.all.isAll(), true);
        assertEquals(TestEnum.e1.isAll(), false);
    }

    @Test
    public void nullCompareTest() {
        TestEnum testEnum = null;
        assertThat(TestEnum.all == testEnum).isFalse();
        assertThat(TestEnum.all.equals(testEnum)).isFalse();
    }



    enum ServerZone {
        D2D3(true, "#log_htmlgen_alarm") {
            @Override
            protected Set<String> getHostNames() {
                Set<String> hostNames = new HashSet<>();
                hostNames.add("vfrontbatch01");
                hostNames.add("tmbatch1");
                return hostNames;
            }
        },
        D1(false, "#log_htmlgen_alarm_d1") {
            @Override
            protected Set<String> getHostNames() {
                Set<String> hostNames = new HashSet<>();
                hostNames.add("11DEPLOYb-statR01.br.skp");
                return hostNames;
            }
        },
        DEV(false, "#log_htmlgen_alarm_dev") {
            @Override
            protected Set<String> getHostNames() {
                return new HashSet<>();
            }
        };

        private final boolean sendSms;
        private final String slackChannel;

        ServerZone(boolean sendSms, String slackChannel) {
            this.sendSms = sendSms;
            this.slackChannel = slackChannel;
        }

        abstract protected Set<String> getHostNames();

        public static ServerZone findByHostName(String hostName) {
            for ( ServerZone serverZone : ServerZone.values() ) {
                if ( serverZone.getHostNames().contains(hostName) ) {
                    return serverZone;
                }
            }
            return ServerZone.DEV;
        }

        public static String findSlackChannelByHostName(String hostName) {
            return findByHostName(hostName).slackChannel;
        }

        public static ServerZone get(String name) {
            try {
                return ServerZone.valueOf(name);
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
    }

    enum TestEnum {
        all,
        e1,
        e2;

        public boolean isAll() {
            return this.equals(all);
        }
    }


}
