package net.vladitandlplayer.lithos.scene.objects;

import net.vladitandlplayer.lithos.scene.SceneObject;
import org.joml.Vector3f;

public class CubeObject extends SceneObject {
    public Vector3f position = new Vector3f(0, 0, 0);
    public Vector3f rotation = new Vector3f(0, 0, 0); //pitch, yaw, roll
    public Vector3f scale = new Vector3f(1, 1, 1);

    public CubeObject() {}
}
