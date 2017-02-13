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
            

        if (other.tag == "Bestia")
        {
           // other.GetComponent<BestiaController>().izquierda = !(other.GetComponent<BestiaController>().izquierda);
            Debug.Log("BESTIA");
        }

    }

    void OnTriggerExit(Collider other)
    {
        if (other.name == "MC")
            other.GetComponent<PlayerCont>().suelo = false;

        if (other.name == "MaloAvanza")
        {
            Debug.Log("BESTIA");
            other.GetComponent<BestiaController>().izquierda = !(other.GetComponent<BestiaController>().izquierda);
            
        }
           

    }

}
