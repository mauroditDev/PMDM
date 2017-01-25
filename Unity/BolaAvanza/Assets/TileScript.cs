using UnityEngine;
using System.Collections;

public class TileScript : MonoBehaviour {

    //public TileManager tileManager;

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}

    void OnTriggerExit(Collider other)
    {
        Debug.Log(other.gameObject.name);
        //tileManager.newTile()
    }

}
