package com.zemult.yovollserver.util.code64;

/**
 * Created by Wikison on 2016/4/13.
 */
public interface BinaryDecoder extends Decoder {
    byte[] decode(byte[] var1) throws DecoderException;
}
