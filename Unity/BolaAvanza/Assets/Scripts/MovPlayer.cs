using UnityEngine;
using System.Collections;

public class MovPlayer : MonoBehaviour {

    public float velocidadBola;
    private Vector3 dir;

	void Start () {
        dir = new Vector3(0, 0);
	}
	
	// Update is called once per frame
	void Update () {
        if (Input.GetMouseButtonDown(0))
        {
            if (dir == new Vector3(-1, 0))
            {
                dir = new Vector3(0, 0, 1);
            }
            else
            {
                dir = new Vector3(-1, 0);
            }
        }

        this.transform.position += velocidadBola * dir * Time.deltaTime;

        if(this.transform.position.y < -3f)
        {
           // Debug.Log("Eres un maldito perdedor");
        }

	}
}
