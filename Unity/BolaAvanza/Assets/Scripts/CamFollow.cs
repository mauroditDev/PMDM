using UnityEngine;
using System.Collections;

public class CamFollow : MonoBehaviour {
    public GameObject target;
    private Vector3 offset;
    public MovPlayer mp;
	// Use this for initialization
	void Start () {
        offset = this.transform.position - target.transform.position;

    }
	
	// Update is called once per frame
	void Update () {
        if(mp.juego)
            transform.position = target.transform.position + offset;
	}
}
