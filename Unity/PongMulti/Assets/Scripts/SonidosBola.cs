using UnityEngine;
using System.Collections;

public class SonidosBola : MonoBehaviour {

    public AudioSource rebote;

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	    
	}
    void OnCollisionEnter(Collision otro){
        rebote.Play();
    }
}
