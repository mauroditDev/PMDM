using UnityEngine;
using System.Collections;

public class TileScript : MonoBehaviour {

    private TileManager tileManager;

	// Use this for initialization
	void Start () {
        tileManager = TileManager.Instance;
    }
	
	// Update is called once per frame
	void Update () {
	
	}

    void OnTriggerExit(Collider other)
    {
        if(other.name == "Player")
        {
            tileManager.newTile();
        }
        Invoke("caer", 1.5f);
    }

    void caer()
    {
        transform.GetChild(0).gameObject.GetComponent<Rigidbody>().useGravity = true;
        transform.GetChild(0).gameObject.GetComponent<Rigidbody>().isKinematic = false ;
        Invoke("destruir", 0.5f);
    }

    void destruir()
    {
        Destroy(this.gameObject);
    }
}
