using UnityEngine;
using System.Collections;

public class TileController : MonoBehaviour {

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}
    void OnTriggerStay(Collider other)
    {
        if (other.name == "MC")
        {
            other.GetComponent<PlayerCont>().suelo = true;
        }

    }

    void OnTriggerExit(Collider other)
    {
        if (other.name == "MC")
            other.GetComponent<PlayerCont>().suelo = false;

    }

}
