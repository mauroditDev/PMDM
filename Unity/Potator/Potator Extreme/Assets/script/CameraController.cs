using UnityEngine;
using System.Collections;

public class CameraController : MonoBehaviour {

    public GameObject player;
    private Vector3 offset;

	// Use this for initialization
	void Start () {
        offset = player.transform.position - this.transform.position;
	}
	
	// Update is called once per frame
	void Update () {
        this.transform.position = player.transform.position + offset;
	}
}
