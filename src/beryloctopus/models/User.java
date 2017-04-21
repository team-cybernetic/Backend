package beryloctopus.models;

import beryloctopus.models.posts.UserPost;
import java.security.PublicKey;

import java.util.List;
import java.util.UUID;

public class User implements beryloctopus.User {
    //The user's UUID
    private PublicKey pubKey;
    //The most recent name for the user
    private String name;
    //The most recent avatar URL for the user
    private String avatarUrl;
    //The wallet associated with the user
    private Wallet wallet;
    //List of UserPosts that represent revisions to the User overtime.
    //The last one is the most recent.
    private List<UserPost> userRevisions;


    /*

    //some old code from another project which may be useful later
    
    public static final int IDENTITY_LENGTH = 91; 
    public static final PublicIdentity ANY = new PublicIdentity((PublicKey) null);

    protected PublicKey pub;
    private byte[] pubEncoded;

    protected final void init(PublicKey pubkey) {
        this.pub = pubkey;
        if (pubkey != null) {
            pubEncoded = this.pub.getEncoded();
        } else {
            pubEncoded = ByteBuffer.allocate(IDENTITY_LENGTH).array();
        }   
    }   

    protected PublicIdentity() {
        pub = null;
        pubEncoded = null;
    }   

    public PublicIdentity(PublicKey pubkey) {
        init(pubkey);
    }   

    public PublicIdentity(byte[] pubkey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (isPubkeyEmpty(pubkey)) {
            init(null);
        } else {
            KeyFactory keyFactory = KeyFactory.getInstance(AlgorithmFactory.getKeypairAlgorithm());
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(pubkey);
            PublicKey pubK = keyFactory.generatePublic(keySpec);
            init(pubK);
        }   
    }   
    
    */

    private String pubkeyToUsername(byte[] pubkey) {
        return (new String(pubkey));
        //TODO: return hash of public key
    }

    public User(byte[] pubkey) {
        this.name = pubkeyToUsername(pubkey);
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public PublicKey getPublicKey() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean verifyMessage(byte[] message, byte[] signature) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
