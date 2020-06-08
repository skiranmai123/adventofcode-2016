package adventofcode.kiranmai.day04;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;


public class SecurityThroughObscurityTest {


    @Test
    public void shouldFindValidRooms() throws Exception {
        String encryptedRooms = readAllLinesOfClassPathResource("/day04/SecurityThroughObscurity.txt");
        long expectedValidRoomSectorIdSum = 137896;

        List<SecurityThroughObscurity.EncyptedRoom> encyptedRoomList = SecurityThroughObscurity.parseEncryptedRoomList(encryptedRooms);
        long validRoomSectorIdSum = encyptedRoomList.stream()
                .filter(SecurityThroughObscurity.EncyptedRoom::isValid)
                .mapToInt((encryptedRoom) -> encryptedRoom.sectorId)
                .sum();

        Assert.assertEquals("Expect a isValid room sectorId sum", expectedValidRoomSectorIdSum, validRoomSectorIdSum);
    }

    private String readAllLinesOfClassPathResource(String resource) throws IOException, URISyntaxException {
        Path resourcePath = Paths.get(SecurityThroughObscurityTest.class.getResource(resource).toURI());
        return String.join("\n", Files.readAllLines(resourcePath));
    }


    @Test
    public void shouldParseEncryptedRoom_valid_123_abxyz() {
        String encryptedRooms = "aaaaa-bbb-z-y-x-123[abxyz]";
        String expectedRoomName = "aaaaa-bbb-z-y-x";
        int expectedSectorId = 123;
        String expectedRoomNameChecksum = "abxyz";

        SecurityThroughObscurity.EncyptedRoom encyptedRoom = SecurityThroughObscurity.parseEncryptedRoom(encryptedRooms);

        Assert.assertEquals("roomName", expectedRoomName, encyptedRoom.roomName);
        Assert.assertEquals("sectorId", expectedSectorId, encyptedRoom.sectorId);
        Assert.assertEquals("checksum", expectedRoomNameChecksum, encyptedRoom.checksum);
        Assert.assertTrue("isValid true", encyptedRoom.isValid());
    }

    @Test
    public void shouldParseEncryptedRoom_valid_987_abcde() {
        String encryptedRooms = "a-b-c-d-e-f-g-h-987[abcde]";
        String expectedRoomName = "a-b-c-d-e-f-g-h";
        int expectedSectorId = 987;
        String expectedRoomNameChecksum = "abcde";

        SecurityThroughObscurity.EncyptedRoom encyptedRoom = SecurityThroughObscurity.parseEncryptedRoom(encryptedRooms);

        Assert.assertEquals("roomName", expectedRoomName, encyptedRoom.roomName);
        Assert.assertEquals("sectorId", expectedSectorId, encyptedRoom.sectorId);
        Assert.assertEquals("checksum", expectedRoomNameChecksum, encyptedRoom.checksum);
        Assert.assertTrue("isValid true", encyptedRoom.isValid());
    }

    @Test
    public void shouldParseEncryptedRoom_invalid_404_oarel() {
        String encryptedRooms = "not-a-real-room-404[oarel]";
        String expectedRoomName = "not-a-real-room";
        int expectedSectorId = 404;
        String expectedRoomNameChecksum = "oarel";

        SecurityThroughObscurity.EncyptedRoom encyptedRoom = SecurityThroughObscurity.parseEncryptedRoom(encryptedRooms);

        Assert.assertEquals("roomName", expectedRoomName, encyptedRoom.roomName);
        Assert.assertEquals("sectorId", expectedSectorId, encyptedRoom.sectorId);
        Assert.assertEquals("checksum", expectedRoomNameChecksum, encyptedRoom.checksum);
        Assert.assertTrue("isValid true", encyptedRoom.isValid());
    }

    @Test
    public void shouldParseEncryptedRoom_invalid_200_decoy() {
        String encryptedRooms = "totally-real-room-200[decoy]";
        String expectedRoomName = "totally-real-room";
        int expectedSectorId = 200;
        String expectedRoomNameChecksum = "decoy";

        SecurityThroughObscurity.EncyptedRoom encyptedRoom = SecurityThroughObscurity.parseEncryptedRoom(encryptedRooms);

        Assert.assertEquals("roomName", expectedRoomName, encyptedRoom.roomName);
        Assert.assertEquals("sectorId", expectedSectorId, encyptedRoom.sectorId);
        Assert.assertEquals("checksum", expectedRoomNameChecksum, encyptedRoom.checksum);
        Assert.assertFalse("isValid false", encyptedRoom.isValid());
    }


    // --- part 2

    @Test
    public void shouldDecryptRoomName() {
        String encryptedRooms = "qzmt-zixmtkozy-ivhz-343";
        String expectedRoomName = "qzmt-zixmtkozy-ivhz";
        String expectedDecryptedRoomName = "very encrypted name";
        int expectedSectorId = 343;

        SecurityThroughObscurity.EncyptedRoom encyptedRoom = SecurityThroughObscurity.parseEncryptedRoom(encryptedRooms);

        Assert.assertEquals("roomName", expectedRoomName, encyptedRoom.roomName);
        Assert.assertEquals("sectorId", expectedSectorId, encyptedRoom.sectorId);
        Assert.assertEquals("decryptedRoomName", expectedDecryptedRoomName, encyptedRoom.decryptRoomName());
    }

    @Test
    public void shouldDecryptRoomName_search_northpole_object_storage() throws Exception {
        int expectedSectorId = 501;
        String wantedRoomName = "northpole object storage";
        String encryptedRooms = readAllLinesOfClassPathResource("/day04/SecurityThroughObscurity.txt");

        List<SecurityThroughObscurity.EncyptedRoom> encyptedRoomList = SecurityThroughObscurity.parseEncryptedRoomList(encryptedRooms);
        Optional<SecurityThroughObscurity.EncyptedRoom> foundRoom = encyptedRoomList.stream()
                .filter((encyptedRoom -> encyptedRoom.decryptRoomName().equals(wantedRoomName)))
                .findAny();

        Assert.assertTrue("found room isPresent", foundRoom.isPresent());
        Assert.assertEquals("found room has expected sectorId", expectedSectorId, foundRoom.get().sectorId);
    }


}
